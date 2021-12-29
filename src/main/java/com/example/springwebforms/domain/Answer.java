package com.example.springwebforms.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private Question question;

    private String author;
    private String answer;
    private Date date;

    public Answer(Question question, String author, String answer) {
        this.question = question;
        this.author = author;
        this.answer = answer;
        this.date = new Date();
    }

    protected Answer() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }
}
