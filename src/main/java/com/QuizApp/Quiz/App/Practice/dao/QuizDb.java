package com.QuizApp.Quiz.App.Practice.dao;

import com.QuizApp.Quiz.App.Practice.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDb extends JpaRepository<Quiz, Integer> {
}
