package com.zum.study.proxy;

/**
 * Created by Joeylee on 2017-10-22.
 */
public class HelloTarget implements Hello{

    public String sayHello(String name) {
        return "Hello, " + name;
    }

    public String sayHi(String name) {
        return "Hi, " + name;
    }

    public String sayThankYou(String name) {
        return "Thank you, " + name;
    }
}
