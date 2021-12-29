package com.example.springwebforms.controller;

import com.example.springwebforms.domain.Answer;
import com.example.springwebforms.domain.Form;
import com.example.springwebforms.loggers.EventLogger;
import com.example.springwebforms.loggers.event.Event;
import com.example.springwebforms.loggers.event.EventType;
import com.example.springwebforms.repos.AnswerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping("answer/stats")
@PreAuthorize("hasAuthority('ADMIN')")
public class AnswerStatsController {

    @Autowired
    private EventLogger eventLogger;

    @Autowired
    private AnswerRepo answerRepo;

    @GetMapping("{form}")
    public String answerShow(
            @PathVariable Form form,
            Model model,
            HttpServletRequest request
    ) {
        eventLogger.logEvent(Event.level(EventType.INFO).that(request.getRemoteHost() + " seeing answer for " + form.getId() + " " + form.getName()).now());

        var answers = new ArrayList<Answer>();
        for(var question : form.getQuestions()) {
            answers.addAll(answerRepo.findAnswerByQuestion(question));
        }

        model.addAttribute("form", form);
        model.addAttribute("answers", answers);

        return "answerStats";
    }
}
