package com.apisae.api.exceptions;

public class NotUniqueUserEx extends RuntimeException{

    public NotUniqueUserEx(String message){
        super(message);
    }

}
