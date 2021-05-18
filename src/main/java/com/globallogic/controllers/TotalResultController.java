package com.globallogic.controllers;

import com.globallogic.dto.RequestDto;
import com.globallogic.model.TotalResult;
import com.globallogic.services.TotalResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController()
@Slf4j
public class TotalResultController {

    TotalResultService totalResultService;

    @Autowired
    public TotalResultController(TotalResultService totalResultService) {
        this.totalResultService = totalResultService;
    }


    // Saving results in db and returns as JSON
    @PostMapping("/json")
    public ResponseEntity<TotalResult> getResultAsJson(@RequestBody RequestDto requestDto){
        try {
            TotalResult totalResult = totalResultService.getResult(requestDto.getSentence(), requestDto.getChars());
            return new ResponseEntity<>(totalResult, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception appear at Get method on /globalLogic/json endpoint, error {}", e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    // Saving results in db and returns as String
    @PostMapping("/text")
    public ResponseEntity<String> getResultAsText(@RequestBody RequestDto requestDto){
        try {
            TotalResult totalResult = totalResultService.getResult(requestDto.getSentence(), requestDto.getChars());
            return new ResponseEntity<>(totalResult.toString(), HttpStatus.OK);
        }catch (Exception e) {
            log.error("Exception appear at Get method on /globalLogic/text endpoint, error {}", e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    // Saving results from file in db and returns as JSON
    @PostMapping("/file/json")
    public ResponseEntity<List<TotalResult>> getResultFromFileAsJson(@RequestParam("file") MultipartFile file){
        try {
            List<TotalResult> results = totalResultService.getResultsFromFile(file);
            return new ResponseEntity<>(results, HttpStatus.OK);
        }catch (Exception e) {
            log.error("Exception appear at Get method on /globalLogic/file endpoint, error {}", e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    // Saving results from file in db and returns as String
    @PostMapping("/file/text")
    public ResponseEntity<String> getResultFromFileAsText(@RequestParam("file") MultipartFile file){
        try {
            String results = totalResultService.getResultsFromFileAsText(file);
            return new ResponseEntity<>(results, HttpStatus.OK);
        }catch (Exception e) {
            log.error("Exception appear at Get method on /globalLogic/file endpoint, error {}", e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    // Getting results from db by key in sentence or key in set Of characters - as Json
    @GetMapping("/results/json/{key}")
    public ResponseEntity<List<TotalResult>> getResultAsJsonFromKey(@PathVariable String key){
        try {
            List<TotalResult> results = totalResultService.findByKey(key);
            return new ResponseEntity<>(results, HttpStatus.OK);
        }catch (Exception e) {
            log.error("Exception appear at Get method on /globalLogic/file endpoint, error {}", e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    // Getting results from db by key in sentence or key in set Of characters - as String
    @GetMapping("/results/text/{key}")
    public ResponseEntity<String> getResultAsTextFromKey(@PathVariable String key){
        try {
            String results = totalResultService.findByKeyAsText(key);
            return new ResponseEntity<>(results, HttpStatus.OK);
        }catch (Exception e) {
            log.error("Exception appear at Get method on /globalLogic/file endpoint, error {}", e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    //Getting all results from db as Json
    @GetMapping("/results/json")
    public ResponseEntity<List<TotalResult>> getResultsAsJson(){
        try {
            List<TotalResult> results = totalResultService.findAll();
            return new ResponseEntity<>(results, HttpStatus.OK);
        }catch (Exception e) {
            log.error("Exception appear at Get method on /globalLogic/file endpoint, error {}", e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    //Getting all results from db as String
    @GetMapping("/results/json")
    public ResponseEntity<String> getResultsAsText(){
        try {
            String results = totalResultService.findAllAsText();
            return new ResponseEntity<>(results, HttpStatus.OK);
        }catch (Exception e) {
            log.error("Exception appear at Get method on /globalLogic/file endpoint, error {}", e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }



}
