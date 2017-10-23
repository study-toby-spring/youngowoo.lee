package com.zum.study.factory;

import com.zum.study.domain.Message;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by Joeylee on 2017-10-23.
 */
public class MessageFactoryBean implements FactoryBean<Message> {

    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public Message getObject() throws Exception {
        return Message.newMessage(text);
    }

    public Class<?> getObjectType() {
        return Message.class;
    }

    public boolean isSingleton() {
        return false;
    }
}
