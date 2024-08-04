package com.QuizApp.Quiz.App.Practice.dao;

import com.QuizApp.Quiz.App.Practice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDB extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);
}
