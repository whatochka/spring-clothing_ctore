package com.springeshop.exceptions;

public class UserNotAuthorizedException extends RuntimeException{
    public UserNotAuthorizedException(String message){
        super(message);
    }
}
