package com.globallogic.exceptions;

public class CharsNullException extends RuntimeException {

    public CharsNullException(){
        super("Chars are empty");
    }
}
