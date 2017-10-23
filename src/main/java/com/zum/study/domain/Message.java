package com.zum.study.domain;

/**
 * Created by Joeylee on 2017-10-23.
 */
public class Message {

    private String text;

    private Message(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Message newMessage(String text) {
        return new Message(text);
    }
}