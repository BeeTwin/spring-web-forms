package com.example.springwebforms.controller;

import com.example.springwebforms.domain.Form;
import com.example.springwebforms.domain.Question;
import com.example.springwebforms.domain.User;
import com.example.springwebforms.loggers.EventLogger;
import com.example.springwebforms.loggers.event.Event;
import com.example.springwebforms.loggers.event.EventType;
import com.example.springwebforms.repos.FormRepo;
import com.example.springwebforms.repos.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/form")
@PreAuthorize("hasAuthority('ADMIN')")
public class FormController {

    @Autowired
    private EventLogger eventLogger;

    @Autowired
    private FormRepo formRepo;

    @GetMapping
    public String formList(Model model, HttpServletRequest request) {
        eventLogger.logEvent(Event.level(EventType.INFO).that(request.getRemoteHost() + " saw all forms").now());

        var forms = formRepo.findAll();
        model.addAttribute("forms", forms);

        return "forms";
    }
}
