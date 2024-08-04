package com.QuizApp.Quiz.App.Practice.service;

import com.QuizApp.Quiz.App.Practice.dao.QuestionDB;
import com.QuizApp.Quiz.App.Practice.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDB questionDB;


    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDB.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> getQuestionById(int id){
        try {
            if (questionDB.existsById(id)) {
                Optional<Question> question = questionDB.findById(id);
                return new ResponseEntity<>(question, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Question with this id does not exist.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while processing the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDB.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question q) {
        try{
            questionDB.save(q);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> deleteQuestion(int id) {
        if(questionDB.existsById(id)){
            try {
                questionDB.deleteById(id);
                return new ResponseEntity<>("Success", HttpStatus.OK);
            }catch (Exception e){
                e.printStackTrace();
            }
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Question with this id does not exist.", HttpStatus.NOT_FOUND);

    }


    public ResponseEntity<String> updateQuestion(int id, Question q) {
        Question existingQuestion = questionDB.findById(id).orElse(null);
        if(existingQuestion != null){
            try{
                existingQuestion.setQuestion(q.getQuestion());
                existingQuestion.setOption1(q.getOption1());
                existingQuestion.setOption2(q.getOption2());
                existingQuestion.setOption3(q.getOption3());
                existingQuestion.setCategory(q.getCategory());
                existingQuestion.setDifficulty(q.getDifficulty());
                existingQuestion.setRightAns(q.getRightAns());
                questionDB.save(existingQuestion);
                return new ResponseEntity<>("Success", HttpStatus.OK);
            }catch (Exception e){
                e.printStackTrace();
            }
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Question with this id does not exist.", HttpStatus.NOT_FOUND);
    }
}
