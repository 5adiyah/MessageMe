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

    public Users(String name, String email, ArrayList<Message> messages){
        this.name = name;
        this.email = email;
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Message> getMessages(){
        return messages;
    }

    public String getPushId(){
        return pushId;
    }

    public void setPushId(String pushId){
        this.pushId = pushId;
    }
}
