package com.pojo;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Account {
    @Id
    private String username;
    private String password;
    private String telnum;



    public Account(){};

    public Account(String username,String password,String telnum){
        this.username = username;
        this.password = password;
        this.telnum = telnum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelnum() { return telnum; }

    public void setTelnum(String telnum) { this.telnum = telnum; }
}
