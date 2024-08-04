package com.QuizApp.Quiz.App.Practice.service;

import com.QuizApp.Quiz.App.Practice.dao.QuestionDB;
import com.QuizApp.Quiz.App.Practice.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDB questionDB;


    public List<Question> getAllQuestions() {
        return questionDB.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDB.findByCategory(category);
    }

    public String addQuestion(Question q) {
        questionDB.save(q);
        return "Success";
    }

    public String deleteQuestion(int id) {
        questionDB.deleteById(id);
        return "Success";
    }


    public String updateQuestion(int id, Question q) {
        Question existingQuestion = questionDB.findById(id).orElse(null);
        if(existingQuestion != null){
            existingQuestion.setQuestion(q.getQuestion());
            existingQuestion.setOption1(q.getOption1());
            existingQuestion.setOption2(q.getOption2());
            existingQuestion.setOption3(q.getOption3());
            existingQuestion.setCategory(q.getCategory());
            existingQuestion.setDifficulty(q.getDifficulty());
            existingQuestion.setRightAns(q.getRightAns());
            questionDB.save(existingQuestion);
            return "Success";
        }
        return "Question Does Not Exist";
    }
}
