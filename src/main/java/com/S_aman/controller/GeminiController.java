package com.S_aman.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.S_aman.model.Question;
import com.S_aman.model.QuestionResponse;
import com.S_aman.service.GeminiService;

@Controller
@RequestMapping("/api")
public class GeminiController {

    @Autowired
    private GeminiService geminiService;

    // Use volatile or consider SessionScope if multiple users will access this simultaneously.
    // For local testing, these instance variables are fine.
    private List<Question> questions = new ArrayList<>();
    private int currentIndex = 0;
    private List<String> userAnswers = new ArrayList<>();

    @GetMapping("/")
    public String home() {
        return "index";
    }


    @PostMapping("/generate")
    public String generate(@RequestParam String prompt, Model model) {
        QuestionResponse response = geminiService.generateContent(prompt);

        if (response == null || response.getQuestions() == null || response.getQuestions().isEmpty()) {
            model.addAttribute("error", "The AI was unable to generate questions.");
            return "index";
        }

        this.questions = response.getQuestions();
        this.currentIndex = 0;
        this.userAnswers = new ArrayList<>();


        model.addAttribute("currentQuestion", questions.get(currentIndex));
        model.addAttribute("currentIndex", 0); // The current question index
        model.addAttribute("totalQuestions", questions.size());

        return "quiz";
    }

    @PostMapping("/next")
    public String nextQuestion(@RequestParam String answer, Model model) {
        if (questions.isEmpty()) return "redirect:/api/";


        userAnswers.add(answer);
        currentIndex++;

        if (currentIndex >= questions.size()) {
            model.addAttribute("score", calculateScore());
            model.addAttribute("total", questions.size());
            return "result :: resultBody";
        }


        model.addAttribute("currentQuestion", questions.get(currentIndex));
        return "fragments/question :: questionFragment";
    }

    private int calculateScore() {
        int score = 0;
        for (int i = 0; i < userAnswers.size(); i++) {
            String correctAnswer = questions.get(i).getAnswer();
            String userSelected = userAnswers.get(i);

            // Using equalsIgnoreCase and trim to handle AI inconsistencies
            if (correctAnswer != null && userSelected != null &&
                    correctAnswer.trim().equalsIgnoreCase(userSelected.trim())) {
                score++;
            }
        }
        return score;
    }
}