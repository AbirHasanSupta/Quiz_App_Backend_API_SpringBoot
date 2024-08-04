package com.QuizApp.Quiz.App.Practice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class QuestionWrapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;
    private String option1;
    private String option2;
    private String option3;

    public QuestionWrapper(int id, String question, String option1, String option2, String option3) {
        this.option3 = option3;
        this.option2 = option2;
        this.option1 = option1;
        this.question = question;
        this.id = id;
    }
}
