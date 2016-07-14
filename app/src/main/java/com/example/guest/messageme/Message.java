package com.example.guest.messageme;

import org.parceler.Parcel;

import java.util.Date;

@Parcel
public class Message {
    String sender;
    String recipient;
    String message;
    private String pushId;

    public Message(){}

    public Message(String message, String sender, String recipient){
        this.message = message;
        this.sender = sender;
        this.recipient = recipient;
    }

    public String getMessage(){
        return message;
    }

    public String getSender(){
        return sender;
    }

    public String getRecipient(){
        return recipient;
    }


    public String getPushId(){
        return pushId;
    }

    public void setPushId(String pushId){
        this.pushId = pushId;
    }
}
