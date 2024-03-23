package org.example.eco_v2.common.exceptions;

public class WrongInputException extends RuntimeException{
    public WrongInputException(String message){
        super(message);
    }
}
