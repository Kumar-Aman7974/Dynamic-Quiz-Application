package com.S_aman.model;


import lombok.Data;

import java.util.List;

@Data
public class AnswerRequest {

    private  String quizId; // which quiz are you answering?
    private List<String> answers; //User's selected answers


}
