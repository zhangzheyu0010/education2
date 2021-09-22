package com.baizhi.zzy.dao;

import com.baizhi.zzy.entity.User;

public interface UserDao {
    public void addUser(User user);

    public User queryUserByPhone(String phone);

    public User queryUserBy(String name);

    public void updateUserByPwd(User user);

}
