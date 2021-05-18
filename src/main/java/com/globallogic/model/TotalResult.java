package com.globallogic.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Results")
public class TotalResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<FrequencyResult> frequencyResults = new ArrayList<>();

    private String sentence;

    //characters searching in sentence
    private String setOfCharacters;

    //amount of characters from setOfCharacters in sentence
    private int totalCharsSum;

    //amount of characters in sentence
    private int allAmountOfCharacters;

    // totalCharsSum / allAmountOfCharacters
    private Double totalCharFrequency;

    public TotalResult() {
    }


    public Double getTotalCharFrequency() {
        return totalCharFrequency;
    }

    public void setTotalFrequency(int sum, int resultChars ) {
        this.setTotalCharsSum(sum);
        this.setAllAmountOfCharacters(resultChars);
        this.totalCharFrequency = Math.round(((double) sum / resultChars) * 100.0) / 100.0;
    }

    public void setFrequencyResults(List<FrequencyResult> frequencyResults) {
        this.frequencyResults = frequencyResults;
    }

    public List<FrequencyResult> getFrequencyResults() {
        return frequencyResults;
    }

    public String getSetOfCharacters() {
        return setOfCharacters;
    }

    public void setSetOfCharacters(String chars) {
        this.setOfCharacters = chars;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public int getTotalCharsSum() {
        return totalCharsSum;
    }

    public void setTotalCharsSum(int sum) {
        this.totalCharsSum = sum;
    }

    public int getAllAmountOfCharacters() {
        return allAmountOfCharacters;
    }

    public void setAllAmountOfCharacters(int resultChars) {
        this.allAmountOfCharacters = resultChars;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }



    public String print(){
        List<String> list = frequencyResults.stream().map(x -> x.toString().replace(",","")).collect(Collectors.toList());
        return Arrays.toString(list.toArray()).replace("[", "").replace("]", "").replace(",","");

    }

    @Override
    public String toString() {
        return print() + "TOTAL Frequency: " + this.getTotalCharFrequency() + " (" + this.getTotalCharsSum() + "/" + this.getAllAmountOfCharacters() + ")";
    }

}
