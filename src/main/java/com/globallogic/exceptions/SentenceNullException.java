package com.globallogic.exceptions;

public class SentenceNullException extends RuntimeException {
    public SentenceNullException(){
        super("The sentence is empty");
    }
}
