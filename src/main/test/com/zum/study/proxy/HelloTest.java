package com.zum.study.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Joeylee on 2017-10-22.
 */
public class HelloTest {

    @Test
    public void targetTest() {

        Hello hello = new HelloTarget();

        assertThat(hello.sayHello("youngwoo"), is("Hello, youngwoo"));
        assertThat(hello.sayHi("youngwooyoungwoo"), is("Hi, youngwoo"));
        assertThat(hello.sayThankYou("youngwoo"), is("Thank you, youngwoo"));
    }

    @Test
    public void proxyTest() {

        HelloProxy proxy = new HelloProxy();
        proxy.setHello(new HelloTarget());

        assertThat(proxy.sayHello("youngwoo"), is("Hello, youngwoo"));
        assertThat(proxy.sayHi("youngwoo"), is("Hi, youngwoo"));
        assertThat(proxy.sayThankYou("youngwoo"), is("Thank you, youngwoo"));
    }

    @Test
    public void handlerTest() {

        HelloHandler handler = new HelloHandler();
        handler.setHello(new HelloTarget());

        Hello target = (Hello) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] { Hello.class }, handler);

        assertThat(target.sayHello("youngwoo"), is("HELLO, youngwoo"));
        assertThat(target.sayHi("youngwoo"), is("HI, youngwoo"));
        assertThat(target.sayThankYou("youngwoo"), is("THANK YOU, youngwoo"));
    }
}
