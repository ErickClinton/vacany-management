package com.management.vacany.vacany.management.exceptions;

public class UserFoundException extends RuntimeException{

    public UserFoundException(){
        super("User already exist.");
    }
}
