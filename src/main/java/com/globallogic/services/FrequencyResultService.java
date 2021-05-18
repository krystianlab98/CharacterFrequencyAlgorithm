package com.globallogic.services;

import com.globallogic.model.FrequencyResult;
import com.globallogic.repositories.FrequencyResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class FrequencyResultService {

    FrequencyResultRepository frequencyResultRepository;

    @Autowired
    public FrequencyResultService(FrequencyResultRepository frequencyResultRepository) {
        this.frequencyResultRepository = frequencyResultRepository;
    }

    public FrequencyResult save(FrequencyResult frequencyResult){
        return frequencyResultRepository.save(frequencyResult);
    }

    public  List<FrequencyResult> algorithmSolution(String sentence , String chars) {
        String[] setOfCharacters = chars.split("");
        final String regexSpecialChars = "(!\"#$%&'()*+,-./:;<=>?@[]^_`{|}~);]";
        final Pattern pattern = Pattern.compile("[" + regexSpecialChars + "]");
        List<FrequencyResult> results;
        String[] words;

        words = getSentenceAsSingleWord(pattern, sentence.split(" "));
        results = identifyWordsWithSetOfChar(setOfCharacters, words);

        results.sort(new FrequencyResult());

        return results;
    }



    private static List<FrequencyResult> identifyWordsWithSetOfChar(String[] setOfCharacters, String[] words) {
        List<String> identifiedChars = new ArrayList();
        List<FrequencyResult> results = new ArrayList<>();
        int sumCharactersFromSentence = 0;
        int sumOfdentifiedCharacters = 0;
        for(String word : words) {
            String[] splitWord = word.toLowerCase(Locale.ROOT).split("");
            identifiedChars.clear();
            int counter = 0;

            for ( int j = 0; j < splitWord.length; j++){
                for (String str : setOfCharacters) {
                    if(str.equals(splitWord[j])){
                        counter++;
                        identifiedChars.add(str);
                    }
                }
            }
            sumCharactersFromSentence += word.length();
            sumOfdentifiedCharacters += counter;
            identifiedChars = identifiedChars.stream().distinct().collect(Collectors.toList());
            identifiedChars.sort(Comparator.comparingInt(Arrays.asList(setOfCharacters)::indexOf));
            String joined = String.join(", ", identifiedChars);

            fillResults(results, word.length(), counter, joined);
        }

        int finalResultChars = sumOfdentifiedCharacters;
        int finalTotalChar = sumCharactersFromSentence;
        return results.stream().map(x -> { x.setAllAmountOfCharacters(finalResultChars); x.setTotalCharsSum(finalTotalChar); return  x;}).collect(Collectors.toList());
    }

    private static void fillResults(List<FrequencyResult> results, int wordLength, int counter, String joined) {
        boolean addNewResult = true;
        FrequencyResult result = new FrequencyResult();

        if(results.isEmpty()){
            result.setIdentifiedChars(joined);
            result.setSumOfCharRepetitions(counter);
            result.setWordLength(wordLength);
        } else {
            for (FrequencyResult x : results) {
                if (x.getWordLength() ==wordLength && x.getIdentifiedChars().equals(joined)) {
                    x.addSum(counter);
                    addNewResult = false;
                } else {
                    result.setIdentifiedChars(joined);
                    result.setSumOfCharRepetitions(counter);
                    result.setWordLength(wordLength);
                }
            }

        }
        if(addNewResult && !joined.equals("")){
            results.add(result);
        }
    }

    private static String[] getSentenceAsSingleWord(Pattern reCharsRep, String[] words) {
        for(int i = 0; i < words.length; i++){
            Matcher m = reCharsRep.matcher(words[i]);
            words[i] = m.replaceAll("");
        }
        words = Arrays.stream(words).filter(x -> !x.isEmpty()).toArray(String[]::new);
        return words;
    }
}
