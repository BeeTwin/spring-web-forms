package com.example.springwebforms.controller;

import com.example.springwebforms.domain.Answer;
import com.example.springwebforms.domain.Form;
import com.example.springwebforms.loggers.EventLogger;
import com.example.springwebforms.loggers.event.Event;
import com.example.springwebforms.loggers.event.EventType;
import com.example.springwebforms.repos.AnswerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping("answer")
public class AnswerController {

    @Autowired
    private EventLogger eventLogger;

    @Autowired
    private AnswerRepo answerRepo;

    @GetMapping("{form}")
    public String answerForm(
            @PathVariable Form form,
            Model model,
            HttpServletRequest request
    ) {
        eventLogger.logEvent(Event.level(EventType.INFO).that(request.getRemoteHost() + " answering form " + form.getId() + " " + form.getName()).now());

        model.addAttribute("form", form);

        return "answer";
    }


    @PostMapping("{form}")
    public String answer(
            @PathVariable Form form,
            @RequestParam String username,
            @RequestParam Map<String, String> answers,
            HttpServletRequest request
    ) {
        eventLogger.logEvent(Event.level(EventType.INFO).that(request.getRemoteHost() + " answered form " + form.getId() + " " + form.getName()).now());

        var questions = new ArrayList<>(form.getQuestions());
        for (var question : questions) {
            var answer = new Answer(question, username, answers.get("answer-"+question.getId()));
            answerRepo.save(answer);
        }

        return "redirect:/main";
    }
}
