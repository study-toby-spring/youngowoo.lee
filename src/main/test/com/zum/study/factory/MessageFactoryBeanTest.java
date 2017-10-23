package com.zum.study.factory;

import com.zum.study.domain.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Joeylee on 2017-10-23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/context/application-context.xml")
public class MessageFactoryBeanTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void getMessageFromFactoryBean() {

        Object message = context.getBean("message");

        assertThat(message instanceof Message, is(true));
        assertThat(((Message) message).getText(), is("youngwoo"));
    }
}
