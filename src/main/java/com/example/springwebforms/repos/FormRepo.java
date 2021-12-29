package com.example.springwebforms.repos;

import com.example.springwebforms.domain.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepo extends JpaRepository<Form, Long> {
}