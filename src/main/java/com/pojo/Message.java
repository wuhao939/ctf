package com.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {
    @Id
    private int id;
    private String username;
    private String contexts;
    private String time;

    public Message(){}
    public Message(String username,String contexts,String time){
        this.username = username;
        this.contexts = contexts;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContexts() {
        return contexts;
    }

    public void setContexts(String contexts) {
        this.contexts = contexts;
    }
}
