package com.zum.study.proxy;

/**
 * Created by Joeylee on 2017-10-22.
 */
public class HelloProxy implements Hello{

    private Hello hello;

    public void setHello(Hello hello) {

        this.hello = hello;
    }


    public String sayHello(String name) {
        return hello.sayHello(name);
    }

    public String sayHi(String name) {
        return hello.sayHi(name);
    }

    public String sayThankYou(String name) {
        return hello.sayThankYou(name);
    }
}
