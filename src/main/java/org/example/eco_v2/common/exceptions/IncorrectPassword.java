package org.example.eco_v2.common.exceptions;


public class IncorrectPassword extends RuntimeException {
    public IncorrectPassword(String message) {
        super(message);
    }
}