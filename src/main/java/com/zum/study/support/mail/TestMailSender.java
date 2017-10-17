package com.zum.study.support.mail;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joeylee on 2017-10-17.
 */
public class TestMailSender implements MailSender {

    private List<String> requests = new ArrayList<String>();

    public List<String> getRequests() {
        return requests;
    }

    public void send(SimpleMailMessage message) throws MailException {

        requests.add(message.getTo()[0]);
    }

    public void send(SimpleMailMessage... messages) throws MailException {

        for (SimpleMailMessage message : messages) {
            send(message);
        }
    }
}

