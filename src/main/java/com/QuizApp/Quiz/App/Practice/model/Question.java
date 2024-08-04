package com.QuizApp.Quiz.App.Practice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;
    private String difficulty;
    private String category;
    private String option1;
    private String option2;
    private String option3;
    private String rightAns;

}
