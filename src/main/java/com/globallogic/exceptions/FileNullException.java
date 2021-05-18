package com.globallogic.exceptions;

public class FileNullException  extends  RuntimeException{

    public FileNullException(){
        super("File is empty");
    }
}
