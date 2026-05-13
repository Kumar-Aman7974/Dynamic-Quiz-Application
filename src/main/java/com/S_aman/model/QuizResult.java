package com.S_aman.model;

import lombok.Data;

import java.util.List;
@Data
public class QuizResult {

    private int score;
    private int total;
    private List<QuestionResult> details;

    public List<QuestionResult> getDetails() {
        return details;
    }

    public void setDetails(List<QuestionResult> details) {
        this.details = details;
    }

    @Data
    public static class QuestionResult {
        private String question;
        private String correctAnswer;
        private String yourAnswer;
        private Boolean isCorrect;

    }
}
