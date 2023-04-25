package com.study.message.models;

public class Message {
    private String fromMessage;
    private String textMessage;
    public String getFromMessage(){
        return  fromMessage;
    }
    public void setFromMessage(String value){
        this.fromMessage=value;
    }

    public String getTextMessage(){
        return  textMessage;
    }
    public void setTextMessage(String value){
        this.textMessage=value;
    }
    public  Message(String from, String text){
        this.textMessage=text;
        this.fromMessage=from;
    }
}
