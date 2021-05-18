package com.globallogic.dto;

public class RequestDto {
    public String sentence;
    public String chars;

    public RequestDto(String sentence, String chars) {
        this.sentence = sentence;
        this.chars = chars;
    }

    public RequestDto() {
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getChars() {
        return chars;
    }

    public void setChars(String chars) {
        this.chars = chars;
    }
}
