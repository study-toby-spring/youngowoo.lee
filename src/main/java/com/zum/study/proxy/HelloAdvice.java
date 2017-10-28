package com.zum.study.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by Joeylee on 2017-10-28.
 */
public class HelloAdvice implements MethodInterceptor {

    public Object invoke(MethodInvocation invocation) throws Throwable {

        String ret = (String) invocation.proceed();
        return ret.toUpperCase();
    }
}
