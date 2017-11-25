package com.zum.study.exception;

public class SqlUpdateFailureException extends RuntimeException {

    public SqlUpdateFailureException(String message) {

        super(message);
    }
}
