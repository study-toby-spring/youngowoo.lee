package com.zum.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Joeylee on 2017-10-22.
 */
public class HelloHandler implements InvocationHandler{

    private Hello hello;

    public void setHello(Hello hello) {
        this.hello = hello;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object ret = method.invoke(hello, args);

        if (ret instanceof String && method.getName().startsWith("say")) {

            return ((String) ret).toUpperCase();
        }
        else {

            return ret;
        }
    }
}
