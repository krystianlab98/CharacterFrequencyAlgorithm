package com.globallogic.services;

import com.globallogic.exceptions.CharsNullException;
import com.globallogic.exceptions.FileNullException;
import com.globallogic.exceptions.SentenceNullException;
import com.globallogic.model.FrequencyResult;
import com.globallogic.model.TotalResult;
import com.globallogic.repositories.TotalResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TotalResultService {

    FrequencyResultService frequencyResultService;
    TotalResultRepository totalResultRepository;

    @Autowired
    public TotalResultService(FrequencyResultService frequencyResultService,
                              TotalResultRepository totalResultRepository) {
        this.frequencyResultService = frequencyResultService;
        this.totalResultRepository = totalResultRepository;
    }

    public TotalResult save(TotalResult totalResult) {
        return totalResultRepository.save(totalResult);
    }

    public List<TotalResult> findByKey(String key){
        return totalResultRepository.findAll(key);
    }

    public String findByKeyAsText(String key){
        return totalResultRepository.findAll(key).toString().replace(",", "\n");
    }

    public List<TotalResult> findAll() {
        return totalResultRepository.findAll();
    }
    public String findAllAsText() {
        return totalResultRepository.findAll().toString().replace(",", "\n");
    }


    public TotalResult getResult(String sentence, String chars) {

        if(sentence.equals(null) || sentence.equals("")){
            throw new SentenceNullException();
        }
        if(chars.equals(null) || sentence.equals("")){
            throw new CharsNullException();
        }

        TotalResult totalResult = new TotalResult();

        List<FrequencyResult> frequencyResults = frequencyResultService.algorithmSolution(sentence, chars);
        totalResult.setFrequencyResults(frequencyResults.stream().map(frequencyResultService::save).collect(Collectors.toList()));
        totalResult.setSentence(sentence);
        totalResult.setSetOfCharacters(chars);
        totalResult.setTotalFrequency(frequencyResults.get(0).getAllAmountOfCharacters() , frequencyResults.get(0).getTotalCharsSum());

        return save(totalResult);

    }

    public List<TotalResult> getResultsFromFile(MultipartFile multipartFile){
        if(multipartFile.isEmpty()){
            throw new FileNullException();
        }
        List<String> lines = new ArrayList<>();
        List<TotalResult> results = new ArrayList<>();

        try {
            String line;
            BufferedReader bufferedReader;
            InputStream inputStream = multipartFile.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = bufferedReader.readLine()) != null){
                lines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < lines.size(); i += 2){
            results.add(this.getResult(lines.get(i), lines.get(i + 1)));
        }
        results.forEach(x -> totalResultRepository.save(x));
        return results;
    }

    public String getResultsFromFileAsText(MultipartFile multipartFile){
        return this.getResultsFromFile(multipartFile).toString().replace(",", "\n");
    }
}
