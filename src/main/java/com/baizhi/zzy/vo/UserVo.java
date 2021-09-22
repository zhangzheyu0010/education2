package com.baizhi.zzy.vo;

public class UserVo {
    private String name;
    private String mobile;
    private String password;
    private String code;

    @Override
    public String toString() {
        return "UserVo{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserVo() {
    }

    public UserVo(String name, String mobile, String password, String code) {
        this.name = name;
        this.mobile = mobile;
        this.password = password;
        this.code = code;
    }
}
