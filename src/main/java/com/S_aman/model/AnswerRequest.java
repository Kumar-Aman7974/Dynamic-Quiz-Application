package com.S_aman.model;


import lombok.Data;

import java.util.List;


public class AnswerRequest {

    private  String quizId; // which quiz are you answering?
    private List<String> answers; //User's selected answers

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }
}
