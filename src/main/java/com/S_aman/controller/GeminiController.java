package com.S_aman.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.S_aman.model.Question;
import com.S_aman.model.QuestionResponse;
import com.S_aman.service.GeminiService;

@RestController
@RequestMapping("/api")
public class GeminiController {


    private  final GeminiService geminiService;

    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }


    @PostMapping("/generate")
    public ResponseEntity<QuestionResponse> generate(@RequestParam(value = "prompt", required = false) String prompt,
                                   @RequestParam(value = "file", required = false) MultipartFile file
                          )
    {
        QuestionResponse response = null;
        if (file != null && !file.isEmpty()) {
            response = geminiService.generateContentFromPdf(file);
        } else if (prompt != null && !prompt.trim().isEmpty()) {
            response = geminiService.generateContent(prompt);
        }
        else {
            // Neither file nor prompt provided
            QuestionResponse empty = new QuestionResponse();
            empty.setQuestions(new ArrayList<>());

    }

        if (response == null || response.getQuestions() == null || response.getQuestions().isEmpty())
        {
            QuestionResponse empty = new QuestionResponse();
            empty.setQuestions(new ArrayList<>());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(empty);
        }

        // Return the actual quiz data as JSON
        return ResponseEntity.ok(response);


    }

}