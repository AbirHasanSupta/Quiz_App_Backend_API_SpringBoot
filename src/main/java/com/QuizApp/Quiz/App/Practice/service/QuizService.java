package com.QuizApp.Quiz.App.Practice.service;

import com.QuizApp.Quiz.App.Practice.dao.QuestionDB;
import com.QuizApp.Quiz.App.Practice.dao.QuizDb;
import com.QuizApp.Quiz.App.Practice.model.Question;
import com.QuizApp.Quiz.App.Practice.model.QuestionWrapper;
import com.QuizApp.Quiz.App.Practice.model.Quiz;
import com.QuizApp.Quiz.App.Practice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDb quizDb;

    @Autowired
    QuestionDB questionDB;

    public ResponseEntity<String> createQuiz(String title, int numQ, String category) {
        try {
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            List<Question> questions = questionDB.findRandomQuestionsByCategory(category, numQ);
            quiz.setQuestions(questions);
            quizDb.save(quiz);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed To Create The Quiz", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Quiz quiz = quizDb.findById(id).orElse(null);
        List<QuestionWrapper> questionList = new ArrayList<>();
        if(quiz != null){
            try{
                List<Question> questions = quiz.getQuestions();
                for(Question q: questions){
                    QuestionWrapper qr = new QuestionWrapper(q.getId(), q.getQuestion(), q.getOption1(), q.getOption2(), q.getOption3());
                    questionList.add(qr);
                }
                return new ResponseEntity<>(questionList, HttpStatus.OK);
            }catch (Exception e){
                e.printStackTrace();
            }
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> calculateResult(int id, List<Response> responses) {
        try{
            Quiz quiz = quizDb.findById(id).get();
            List<Question> questions = quiz.getQuestions();
            int result = 0;
            int i = 0;
            for(Response response: responses){
                if (response.getResponse().equals(questions.get(i).getRightAns())){
                    result ++;
                }
                i ++;
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Wrong Happened.", HttpStatus.BAD_REQUEST);

    }
}
