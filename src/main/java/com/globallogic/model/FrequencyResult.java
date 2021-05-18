package com.globallogic.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Comparator;

@Entity
public class FrequencyResult implements Comparator<FrequencyResult> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //characters found in one word of sentence
    private  String identifiedChars;

    private  int wordLength;

    //sum characters from setOfCharacters(TotalResult class) in one word from sentence
    private  int sumOfCharRepetitions;

    //amount of characters in sentence
    public  int totalCharsSum;

    // totalCharsSum / allAmountOfCharacters
    public  int allAmountOfCharacters;


    public FrequencyResult() {
    }

    public Long getId() {
        return id;
    }


    public int getTotalCharsSum() {
        return totalCharsSum;
    }

    public void setTotalCharsSum(int totalChar) {
        this.totalCharsSum = totalChar;
    }

    public int getAllAmountOfCharacters() {
        return allAmountOfCharacters;
    }

    public void setAllAmountOfCharacters(int resultChars) {
        this.allAmountOfCharacters = resultChars;
    }

    public String getIdentifiedChars() { return identifiedChars; }

    public void setIdentifiedChars(String identifiedChars) { this.identifiedChars = identifiedChars; }

    public int getWordLength() {
        return wordLength;
    }

    public void setWordLength(int wordLength) {
        this.wordLength = wordLength;
    }

    public int getSumOfCharRepetitions() {
        return sumOfCharRepetitions;
    }

    public void setSumOfCharRepetitions(int sum) {
        this.sumOfCharRepetitions = sum;
    }



    public double divide(){
        return  Math.round(((double) sumOfCharRepetitions / allAmountOfCharacters) * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return "{("+this.getIdentifiedChars() +"), "+ this.getWordLength() + "} = " + divide() +" (" + this.getSumOfCharRepetitions() +"/" + allAmountOfCharacters + ")\n" ;
    }

    @Override
    public int compare(FrequencyResult r1, FrequencyResult r2){
        if (r1.getSumOfCharRepetitions() > r2.getSumOfCharRepetitions()) return 1;
        if(r1.getSumOfCharRepetitions() < r2.getSumOfCharRepetitions()) return -1;
        return 0;
    }

    public void addSum(int value){
        this.sumOfCharRepetitions += value;
    }
}
