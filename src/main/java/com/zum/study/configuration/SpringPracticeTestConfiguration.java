package com.zum.study.configuration;

import com.zum.study.factory.MessageFactoryBean;
import com.zum.study.service.TestUserServiceImpl;
import com.zum.study.service.UserService;
import com.zum.study.support.mail.TestMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

@Configuration
public class SpringPracticeTestConfiguration {

    @Bean
    public UserService testUserService() {
        return new TestUserServiceImpl();
    }

    @Bean
    public MailSender mailSender() {
        return new TestMailSender();
    }

    @Bean
    public MessageFactoryBean message() {

        MessageFactoryBean bean = new MessageFactoryBean();
        bean.setText("youngwoo");

        return bean;
    }
}
