package com.example.guest.messageme;

import org.parceler.Parcel;

import java.util.Date;

@Parcel
public class Message {
    private String sender;
    private String recipient;
    private String message;
    private Date date;
    private String pushId;

    public Message(){}

    public Message(String message, String sender, String recipient){
        this.message = message;
        this.sender = sender;
        this.recipient = recipient;
        this.date = new Date();
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

    public Date getDate(){
        return date;
    }

    public String getPushId(){
        return pushId;
    }

    public void setPushId(String pushId){
        this.pushId = pushId;
    }
}
