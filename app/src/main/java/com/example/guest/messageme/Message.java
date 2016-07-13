package com.example.guest.messageme;

import org.parceler.Parcel;

@Parcel
public class Message {
    String message;

    public Message(){}

    public Message(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){this.message = message;}
}
