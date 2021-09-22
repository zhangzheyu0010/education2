package com.baizhi.zzy.service;

import com.baizhi.zzy.entity.User;

public interface UserService {
    public void getAddUser(User user);

    public User getQueryUserByPhone(String phone);

    public User getQueryUserBy(String name,String password);

    public void getUpdateUserByPwd(User user);
}
