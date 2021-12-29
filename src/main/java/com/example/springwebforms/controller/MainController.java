package com.example.springwebforms.controller;

import com.example.springwebforms.domain.Answer;
import com.example.springwebforms.domain.Form;
import com.example.springwebforms.domain.Question;
import com.example.springwebforms.domain.User;
import com.example.springwebforms.loggers.EventLogger;
import com.example.springwebforms.loggers.event.Event;
import com.example.springwebforms.loggers.event.EventType;
import com.example.springwebforms.repos.AnswerRepo;
import com.example.springwebforms.repos.FormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private EventLogger eventLogger;

    @Autowired
    private FormRepo formRepo;

    @Autowired
    private AnswerRepo answerRepo;

    @GetMapping
    public String formList(Map<String, Object> model, HttpServletRequest request) {
        eventLogger.logEvent(Event.level(EventType.INFO).that(request.getRemoteHost() + " saw all forms").now());

        var forms = formRepo.findAll();
        forms = forms.stream().filter(Form::isActive).collect(Collectors.toList());
        model.put("forms", forms);

        return "main";
    }
}
