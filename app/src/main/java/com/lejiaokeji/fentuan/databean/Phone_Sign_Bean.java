package com.lejiaokeji.fentuan.databean;

public class Phone_Sign_Bean {
    String phone;
    String password;
    String code;
    String invitecode;

    public Phone_Sign_Bean(String phone, String password, String code, String invitecode) {
        this.phone = phone;
        this.password = password;
        this.code = code;
        this.invitecode = invitecode;
    }

    public Phone_Sign_Bean() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getInvitecode() {
        return invitecode;
    }

    public void setInvitecode(String invitecode) {
        this.invitecode = invitecode;
    }

}
