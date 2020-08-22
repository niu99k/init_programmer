package com.gg.domain;

import java.io.Serializable;
import java.util.Date;

public class User_test implements Serializable {
    private Integer id;
    private String userName;
    private String userAddress;
    private String userSex;
    private Date userBirthday;

    public User_test() {
    }

    public User_test(Integer id, String userName, String userAddress, String userSex, Date userBirthday) {
        this.id = id;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userSex = userSex;
        this.userBirthday = userBirthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    @Override
    public String toString() {
        return "User_test{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userBirthday=" + userBirthday +
                '}';
    }
}
