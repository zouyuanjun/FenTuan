package com.lejiaokeji.fentuan.databean;

public class Userinfo_Bean {
    String id;
    String phone = "";   //手机号
    String nickname = ""; //昵称
    int sex = 0;//性别
    String address = ""; //地址
    String headportrait = ""; //头像url
    String age;
    int level;
    String commission;
    String jdpid;
    String pddpid;

    public Userinfo_Bean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getJdpid() {
        return jdpid;
    }

    public void setJdpid(String jdpid) {
        this.jdpid = jdpid;
    }

    public String getPddpid() {
        return pddpid;
    }

    public void setPddpid(String pddpid) {
        this.pddpid = pddpid;
    }
}
