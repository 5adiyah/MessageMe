package com.example.guest.messageme;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guest on 7/14/16.
 */
public class Users {
    String name;
    String email;
//    List<Message> messages = new ArrayList<>();
    private String pushId;

    public Users(){}

    public Users(String name, String email){
        this.name = name;
        this.email = email;
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
