package com.shimh.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.shimh.common.entity.BaseEntity;


@Entity
@Table(name = "sys_user")
public class User extends BaseEntity<Long> {

    /**
     *
     */
    private static final long serialVersionUID = -4454737765850239378L;


    @NotBlank
    @Column(name = "account", unique = true, length = 10)
    private String account;

    /**
     * 使用md5(username + original password + salt)加密存储
     */
    @NotBlank
    @Column(name = "password", length = 64)
    private String password;

    /**
     * 头像
     */
    private String avatar;

    @Column(name = "email", unique = true, length = 20)
    private String email;  // 邮箱

    //@NotBlank
    @Column(name = "nickname", length = 10)
    private String nickname;

    /**
     * 性别
     */
    @Column(name = "sex", length = 10)
    private String sex;

    @Column(name = "mobile_phone_number", length = 20)
    private String mobilePhoneNumber;

    ///**
    // * 院系
    // */
    //@Colum(name = "profession", length = 5)
    //private int profession;
    ///**
    // * 爱好
    // */
    //@Colum(name="hobby",length=10)
    //private String hobby;
    //
    ///**
    // * 学历
    // */
    //@Colum(name="education",length=10)
    //private String education;
    //
    ///**
    // * 个人简介
    // */
    //@Colum(name="introduction",length=200)
    //private String introduction;

    /**
     * 加密密码时使用的种子
     */
    private String salt;

    /**
     * 出生年月
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "birthday")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;


    /**
     * 最后一次登录时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    /**
     * 系统用户的状态
     */
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.normal;

    /**
     * 是否是管理员
     */
    private Boolean admin = false;

    /**
     * 逻辑删除flag
     */
    private Boolean deleted = Boolean.FALSE;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    //public int getProfession() {
    //    return profession;
    //}
    //
    //public void setProfession(int profession) {
    //    this.profession = profession;
    //}
    //
    //public Date getBirthday() {
    //    return birthday;
    //}
    //
    //public void setBirthday(Date birthday) {
    //    this.birthday = birthday;
    //}
    //
    //public String getHobby() {
    //    return hobby;
    //}
    //
    //public void setHobby(String hobby) {
    //    this.hobby = hobby;
    //}
    //
    //public String getEducation() {
    //    return education;
    //}
    //
    //public void setEducation(String education) {
    //    this.education = education;
    //}
    //
    //public String getIntroduction() {
    //    return introduction;
    //}
    //
    //public void setIntroduction(String introduction) {
    //    this.introduction = introduction;
    //}

    @Override
    public String toString() {
        return "User [account=" + account + ", password=" + password + ", avatar=" + avatar + ", email=" + email
                + ", nickname=" + nickname + ", sex" + sex +  ", birthday" + birthday
                +   ", mobilePhoneNumber=" + mobilePhoneNumber
                + ", salt=" + salt
                + ", createDate=" + createDate + ", lastLogin=" + lastLogin + ", status=" + status + ", admin=" + admin
                + ", deleted=" + deleted + "]";
    }


}
