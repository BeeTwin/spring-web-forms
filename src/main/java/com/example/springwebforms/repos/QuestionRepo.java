package com.example.springwebforms.repos;

import com.example.springwebforms.domain.Form;
import com.example.springwebforms.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question, Long> {
    Question findByQuestionAndForm(String question, Form form);
}
