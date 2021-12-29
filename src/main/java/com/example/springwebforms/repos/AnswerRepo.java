package com.example.springwebforms.repos;

import com.example.springwebforms.domain.Answer;
import com.example.springwebforms.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepo extends JpaRepository<Answer, Long> {
    List<Answer> findAnswerByQuestion(Question question);
}
