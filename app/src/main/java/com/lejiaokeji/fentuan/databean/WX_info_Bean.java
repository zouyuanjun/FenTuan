package com.lejiaokeji.fentuan.databean;

public class WX_info_Bean {
    String weixinid="";   //微信openid
    String phone="";   //手机号
    String password="";  //密码
    String invitecode ="";  //邀请码
    String nickname=""; //昵称
    String sex=""; //性别
    String address=""; //地址
    String headportrait=""; //头像url
    String code=""; //验证码

    public WX_info_Bean() {
    }

    public WX_info_Bean(String weixinid, String phone, String password, String invitecode, String nickname, String sex, String address, String headportrait, String code) {
        this.weixinid = weixinid;
        this.phone = phone;
        this.password = password;
        this.invitecode = invitecode;
        this.nickname = nickname;
        this.sex = sex;
        this.address = address;
        this.headportrait = headportrait;
        this.code = code;
    }

    public String getWeixinid() {
        return weixinid;
    }

    public void setWeixinid(String weixinid) {
        this.weixinid = weixinid;
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

    public String getInvitecode() {
        return invitecode;
    }

    public void setInvitecode(String invitecode) {
        this.invitecode = invitecode;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeadportrait() {
        return headportrait;
    }

    public void setHeadportrait(String headportrait) {
        this.headportrait = headportrait;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
