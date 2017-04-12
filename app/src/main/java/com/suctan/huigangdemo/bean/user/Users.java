package com.suctan.huigangdemo.bean.user;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/11/15.
 */
public class Users {
    private String nid; /// 管理员ID
    private String UserName;   /// 用户名
    private String nickname;   /// 昵称
    private String Name;  /// 真实姓名
    private String mobile;    /// 手机号码
    private String Email;    /// 邮箱
    private String AddTime;    /// 添加时间

    private  String data;

    private String avatar;    // 头像
    private String avatar1;//头像1
    private String avatar2;//头像2
    private String avatar3;//头像3
    private String avatarSet;  /// 头像上传的设置
    private String signature;    /// 个性签名
    private String IdNumber;//身份证号
    private int sex;//性别
    private String birthday;//出生年月
    private int IsReg;//是否注册教育平台
    private String specialtyName;//专业
    private String educateLevel;//教育水平
    private String professional;//职称

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String Address;//通讯地址
    private String Organizaton;//单位
    private String privince;//单位所属
    private String regTime;//注册时间
    private String regIp;//注册Ip地址
    private String token;//用户登录的token
    private BigDecimal balance;//余额
    private String secureId;

    public String getSecureId() {
        return secureId;
    }

    public void setSecureId(String secureId) {
        this.secureId = secureId;
    }

    public String getAvatar1() {
        return avatar1;
    }

    public void setAvatar1(String avatar1) {
        this.avatar1 = avatar1;
    }

    public String getAvatar2() {
        return avatar2;
    }

    public void setAvatar2(String avatar2) {
        this.avatar2 = avatar2;
    }

    public String getAvatar3() {
        return avatar3;
    }

    public void setAvatar3(String avatar3) {
        this.avatar3 = avatar3;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }


    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


    public String getAddTime() {
        return AddTime;
    }

    public void setAddTime(String addTime) {
        AddTime = addTime;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarSet() {
        return avatarSet;
    }

    public void setAvatarSet(String avatarSet) {
        this.avatarSet = avatarSet;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }


    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getIsReg() {
        return IsReg;
    }

    public void setIsReg(int isReg) {
        IsReg = isReg;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public String getEducateLevel() {
        return educateLevel;
    }

    public void setEducateLevel(String educateLevel) {
        this.educateLevel = educateLevel;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getOrganizaton() {
        return Organizaton;
    }

    public void setOrganizaton(String organizaton) {
        Organizaton = organizaton;
    }

    public String getPrivince() {
        return privince;
    }

    public void setPrivince(String privince) {
        this.privince = privince;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

