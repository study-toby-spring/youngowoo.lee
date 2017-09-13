package com.zum.study.template;

/**
 * Created by Joeylee on 2017-09-13.
 */
public interface LineCallback<T> {

    T doSomethingWithLine(String line, T value);
}
