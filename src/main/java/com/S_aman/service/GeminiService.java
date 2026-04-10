package com.S_aman.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.S_aman.model.QuestionResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeminiService {

	private final ChatClient chatClient;
	private final ObjectMapper objectMapper;

	public GeminiService(ChatClient.Builder builder) {
		this.chatClient = builder.build();
		// Configure Jackson to be "forgiving" of extra fields
		this.objectMapper = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public QuestionResponse generateContent(String prompt) {
		try {
			String aiResponse = chatClient.prompt()
					.system("""
                         You are a strict JSON quiz generator.
                         RULES:
                         1. Return ONLY a valid JSON object. 
                         2. DO NOT include any introductory text, markdown backticks (```), or explanations.
                         3. Generate 5 Multiple Choice Questions.
                         4. Format:
                            {
                              "questions": [
                                {
                                  "question": "The question text",
                                  "options": ["Choice A", "Choice B", "Choice C", "Choice D"],
                                  "answer": "The exact correct string from the options list"
                                }
                              ]
                            }
                         """)
					.user("Topic for quiz: " + prompt)
					.call()
					.content();

			// STEP 1: Clean the response (Crucial for AI stability)
			if (aiResponse == null) return new QuestionResponse();

			// Remove Markdown blocks if the AI ignored the 'no-markdown' rule
			String cleanJson = aiResponse
					.replaceAll("```json", "")
					.replaceAll("```", "")
					.trim();

			// STEP 2: Parse into Object
			return objectMapper.readValue(cleanJson, QuestionResponse.class);

		} catch (Exception e) {
			// Log the error and return an empty response to avoid crashing the controller
			System.err.println("Gemini Service Error: " + e.getMessage());
			QuestionResponse fallback = new QuestionResponse();
			fallback.setQuestions(new java.util.ArrayList<>());
			return fallback;
		}
	}
}