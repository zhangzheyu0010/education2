package com.baizhi.zzy.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private Date bir;
    private String password;
    private String image;
    private Integer status;
    private String phone;
    private Date created_at;
    private Date last_login_time;
    private String salt;

    public User() {
    }

    public User(Integer id, String name, Integer age, Date bir, String password, String image, Integer status, String phone, Date created_at, Date last_login_time, String salt) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bir = bir;
        this.password = password;
        this.image = image;
        this.status = status;
        this.phone = phone;
        this.created_at = created_at;
        this.last_login_time = last_login_time;
        this.salt = salt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBir() {
        return bir;
    }

    public void setBir(Date bir) {
        this.bir = bir;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Date last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", bir=" + bir +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", created_at=" + created_at +
                ", last_login_time=" + last_login_time +
                ", salt='" + salt + '\'' +
                '}';
    }
}
