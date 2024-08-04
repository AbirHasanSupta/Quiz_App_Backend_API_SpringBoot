package com.QuizApp.Quiz.App.Practice.controller;


import com.QuizApp.Quiz.App.Practice.model.QuestionWrapper;
import com.QuizApp.Quiz.App.Practice.model.Response;
import com.QuizApp.Quiz.App.Practice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String title, @RequestParam int numQ, @RequestParam String category){
        return quizService.createQuiz(title, numQ, category);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<?> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }
}
