package com.baizhi.zzy.controller;

import com.baizhi.zzy.constants.RedisPrefix;
import com.baizhi.zzy.entity.User;
import com.baizhi.zzy.service.UserService;
import com.baizhi.zzy.util.Md5Utils;
import com.baizhi.zzy.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static String PATTEN_REGEX_PHONE= "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$";

    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    @GetMapping("judgePhone")
    public String judgePhone(String phone){
        if (!phone.matches(PATTEN_REGEX_PHONE)) {
            return "error";
        }else{
            return  "success";
        }
    }

    //验证码
    @PostMapping("captcha/{phone}")
    public void captcha(@PathVariable("phone") String phone){

        if (!phone.matches(PATTEN_REGEX_PHONE)) {
            throw new RuntimeException("请填写正确的手机号格式");
        }

        String redisKey = RedisPrefix.CODE_FREQUENCY+phone;
        if(stringRedisTemplate.hasKey(redisKey)){
            throw new RuntimeException("您已经在60秒内发送过验证码了~");
        }
        try{

            String code = "";
            while (code.length() < 6) {
                String str = String.valueOf((int)(Math.random()*10));
                if(code.indexOf(str) == -1){
                    code += str;
                }
            }
           /* SMSUtils.sendMsg(phone,code);*/
            //将验证码存入redis,限制发送频率，验证码的有效时间
            String phoneKey = RedisPrefix.EXPIRE_TIME + phone;
            stringRedisTemplate.opsForValue().set(phoneKey, code, 5, TimeUnit.MINUTES);    // code存活时间
            stringRedisTemplate.opsForValue().set(redisKey, code, 60, TimeUnit.SECONDS);    // 发送频率
            System.out.println(code);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("验证码发送失败");
        }
    }


    //用户注册
    @PostMapping("addUser")
    public Map<String,Object> addUser(@RequestBody UserVo userVo ){
        HashMap<String, Object> result = new HashMap<>();
        String redisKey = RedisPrefix.EXPIRE_TIME+userVo.getMobile();
        if(stringRedisTemplate.boundValueOps(redisKey).get().equals(userVo.getCode())==false){
            throw new RuntimeException("输入的验证码不正确,请重新输入");
        }
        //随机生成用户名
        String ret = "";
        for (int i = 0; i < 6; i++) {
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); // 获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); // 获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                str = new String(b, "GBK"); // 转成中文
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            ret += str;
        }
        //随机生成六位数
        int max=100,min=1;
        int ran2 = (int) (Math.random()*(max-min)+min);
        String star=Md5Utils.getSalt(ran2);
        String pwd=Md5Utils.getMd5Code(userVo.getPassword()+star);
        User user = new User();
        user.setName(ret);
        user.setPassword(pwd);
        user.setPhone(userVo.getMobile());
        user.setSalt(star);
        userService.getAddUser(user);
        //将用户保存到redis中
        String token = DigestUtils.md5DigestAsHex(UUID.randomUUID().toString().getBytes());
        stringRedisTemplate.opsForValue().set(RedisPrefix.EXPIRE_TIME+token,user.getName(),20,TimeUnit.MINUTES);
        result.put("success",true);
        result.put("username",user.getName());
        result.put("token",token);
        return result;
    }

    //判断手机号是否存在
    @GetMapping("queryUsrByPhone/{phone}")
    public String queryUsrByPhone(@PathVariable("phone") String phone){
       User user= userService.getQueryUserByPhone(phone);
        if(user==null){
            return "success";
        }else{
            return "error";
        }
    }


    @GetMapping("login")
    public Map<String,Object> login(String name,String password){
        HashMap<String, Object> result = new HashMap<>();
        try{
            User us=userService.getQueryUserBy(name,password);
            String token = DigestUtils.md5DigestAsHex(UUID.randomUUID().toString().getBytes());
            stringRedisTemplate.opsForValue().set(RedisPrefix.EXPIRE_TIME+token,us.getName(),20,TimeUnit.MINUTES);
            result.put("success",true);
            result.put("username",us.getName());
            result.put("token",token);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
        }
        return result;
    }

    @GetMapping("loginByPhone")
    public Map<String,Object> loginByPhone(String phone,String code){
        Map<String,Object> result = new HashMap<>();
        User user =userService.getQueryUserByPhone(phone);
        try{
            if(user==null){
                throw new RuntimeException("手机号不能为空，请重新输入~~");
            }
            String redisKey = RedisPrefix.EXPIRE_TIME+phone;
            if(stringRedisTemplate.boundValueOps(redisKey).get().equals(code)==false){
                throw new RuntimeException("输入的验证码不正确,请重新输入");
            }
            String token = DigestUtils.md5DigestAsHex(UUID.randomUUID().toString().getBytes());
            stringRedisTemplate.opsForValue().set(RedisPrefix.EXPIRE_TIME+token,user.getName(),20,TimeUnit.MINUTES);
            result.put("success",true);
            result.put("username",user.getName());
            result.put("token",token);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
        }
        return result;
    }


    @GetMapping("verify")
    public Integer verify(String token){
        Boolean ab = stringRedisTemplate.hasKey(RedisPrefix.EXPIRE_TIME + token);
        if(ab==true){
            return 1;
        }else{
            return 0;
        }
    }

    //退出系统
    @DeleteMapping("deleteToken/{token}")
    public void deleteToken(@PathVariable("token") String token){
        stringRedisTemplate.delete(RedisPrefix.EXPIRE_TIME + token);
    }


    //修改用户信息
    @PutMapping("updateUser")
    public void updateUser(@RequestBody  UserVo userVo){
        System.out.println(userVo);
        String redisKey = RedisPrefix.EXPIRE_TIME+userVo.getMobile();
        if(stringRedisTemplate.boundValueOps(redisKey).get().equals(userVo.getCode())==false){
            throw new RuntimeException("输入的验证码不正确,请重新输入");
        }
        User userOne = userService.getQueryUserByPhone(userVo.getMobile());
        String pwd=Md5Utils.getMd5Code(userVo.getPassword()+userOne.getSalt());
        User userTow = new User(userOne.getId(),null,null,null,pwd,null,null,null,null,null,null);
        userService.getUpdateUserByPwd(userTow);
    }

}
