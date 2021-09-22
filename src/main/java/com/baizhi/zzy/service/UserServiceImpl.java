package com.baizhi.zzy.service;


import com.baizhi.zzy.dao.UserDao;
import com.baizhi.zzy.entity.User;
import com.baizhi.zzy.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void getAddUser(User user) {
        userDao.addUser(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public User getQueryUserByPhone(String phone){
       User user= userDao.queryUserByPhone(phone);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public User getQueryUserBy(String name,String password) {
       User us = userDao.queryUserBy(name);
       String salt = us.getSalt();
       if(us!=null){
           if(Md5Utils.getMd5Code(password+salt).equals(us.getPassword())){
               return us;
           }
           throw new RuntimeException("密码不一致");
       }
       throw new RuntimeException("用户不存在");
    }

    @Override
    public void getUpdateUserByPwd(User user) {
        userDao.updateUserByPwd(user);
    }


}
