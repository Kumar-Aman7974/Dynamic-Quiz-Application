package com.S_aman.model;

import java.util.List;


public class QuestionResponse {

	private String quizId;  // unique ID for each quiz
	private List<Question> questions;


	// here we are creating the getter and setter method for the questions list;

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
