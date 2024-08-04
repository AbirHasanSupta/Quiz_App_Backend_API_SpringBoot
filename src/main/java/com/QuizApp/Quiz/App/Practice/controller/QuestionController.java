package com.QuizApp.Quiz.App.Practice.controller;


import com.QuizApp.Quiz.App.Practice.model.Question;
import com.QuizApp.Quiz.App.Practice.service.QuestionService;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public String addQuestion(@RequestBody Question q){
        return questionService.addQuestion(q);
    }

    @DeleteMapping("delete/{id}")
    public String deleteQuestion(@PathVariable int id){
        return questionService.deleteQuestion(id);
    }

    @PutMapping("update/{id}")
    public String updateQuestion(@PathVariable int id, @RequestBody Question q){
        return questionService.updateQuestion(id, q);
    }


}
