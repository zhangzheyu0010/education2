package com.baizhi.zzy.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class SMSUtils {

    private static String ACCESSKEYID = "LTAI5tBJGw3Q2Dy8K24q7x26";
    private static String ACCESSSECRET = "20x2xH6jyC58I6MhHxeyM5dr4BImeq";

    public static void sendMsg(String phone, String code) {
        //自己账号的AccessKey信息
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESSKEYID, ACCESSSECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);  // 发送短信的请求的类型
        request.setSysDomain("dysmsapi.aliyuncs.com");// 代表短信服务的服务接入地址
        request.setSysVersion("2017-05-25");//API的版本号
        request.setSysAction("SendSms");//API的名称  可以一次向多个手机号发送同样内容的短信
        request.putQueryParameter("PhoneNumbers", phone);//接收短信的手机号码
        request.putQueryParameter("SignName", "毛信宇");//短信签名名称
        request.putQueryParameter("TemplateCode", "SMS_109480109");//短信模板ID
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");//短信模板变量对应的实际值

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
            throw new RuntimeException("阿里云服务异常");
        } catch (ClientException e) {
            e.printStackTrace();
            throw new RuntimeException("短信发送异常");
        }
    }

    public static void main(String[] args) {
        SMSUtils.sendMsg("18500230996", "888888");
    }
}
