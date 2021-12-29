package com.example.springwebforms.domain;

import javax.persistence.*;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "form_id")
    private Form form;

    private String question;

    public Question(String question, Form form) {
        this.question = question;
        this.form = form;
    }

    protected Question() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Form getForm() {
        return form;
    }
}
