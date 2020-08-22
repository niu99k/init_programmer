package com.gg.domain;

import java.util.Date;

public class User {
    private Integer uid;
    private String uname;
    private Date ubirthday;
    private String usex;
    private String uaddress;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Date getUbirthday() {
        return ubirthday;
    }

    public void setUbirthday(Date ubirthday) {
        this.ubirthday = ubirthday;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public String getUaddress() {
        return uaddress;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uName='" + uname + '\'' +
                ", ubirthday=" + ubirthday +
                ", usex='" + usex + '\'' +
                ", uaddress='" + uaddress + '\'' +
                '}';
    }
}
