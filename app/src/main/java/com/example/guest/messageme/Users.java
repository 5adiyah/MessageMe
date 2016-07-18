package com.example.guest.messageme;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guest on 7/14/16.
 */
@JsonIgnoreProperties (ignoreUnknown = true)
public class Users {
    String name;
    String email;
    private String pushId;

    public Users(){}

    public Users(String name, String email, String pushId){
        this.name = name;
        this.email = email;
        this.pushId = pushId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPushId(){
        return pushId;
    }

    public void setPushId(String pushId){
        this.pushId = pushId;
    }
}
