package org.twspring.project3.Api;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}