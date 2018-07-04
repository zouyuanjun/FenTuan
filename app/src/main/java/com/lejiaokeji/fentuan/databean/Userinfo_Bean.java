package com.lejiaokeji.fentuan.databean;

public class Userinfo_Bean {
    /**id*/
    private Integer id;
    /**用户名*/
    private String nickname;
    /**性别*/
    private Integer sex;
    /**年龄*/
    private Integer age;
    /**用户头像*/
    private String headportrait;
    /**地址*/
    private String address;
    /**手机号*/
    private String phone;
    /**等级*/
    private Integer level;
    /**佣金*/
    private float commission;
    /**京东推广id*/
    private String jdpid;
    /**拼多多推广位id*/
    private String pddpid;
    /**微信id*/
    private String weixinNumber;
    /**上级邀请码*/
    private String superInvitecode;
    /**收益*/
    private float earnings;
    /**团队人数*/
    private Integer teamNumbers;
    /**直接人数*/
    private Integer directly;
    /**间接人数*/
    private Integer indirect;
    public Integer getDirectly() {
        return directly;
    }
    public void setDirectly(Integer directly) {
        this.directly = directly;
    }
    public Integer getIndirect() {
        return indirect;
    }
    public void setIndirect(Integer indirect) {
        this.indirect = indirect;
    }
    public Integer getTeamNumbers() {
        return teamNumbers;
    }
    public void setTeamNumbers(Integer teamNumbers) {
        this.teamNumbers = teamNumbers;
    }
    public String getWeixinNumber() {
        return weixinNumber;
    }
    public void setWeixinNumber(String weixinNumber) {
        this.weixinNumber = weixinNumber;
    }
    public String getSuperInvitecode() {
        return superInvitecode;
    }
    public void setSuperInvitecode(String superInvitecode) {
        this.superInvitecode = superInvitecode;
    }
    public float getEarnings() {
        return earnings;
    }
    public void setEarnings(float earnings) {
        this.earnings = earnings;
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
    public Float getCommission() {
        return commission;
    }
    public void setCommission(float commission) {
        this.commission = commission;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getHeadportrait() {
        return headportrait;
    }
    public void setHeadportrait(String headportrait) {
        this.headportrait = headportrait;
    }
    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }
}
