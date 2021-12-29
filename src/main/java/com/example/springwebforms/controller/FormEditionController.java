package com.example.springwebforms.controller;

import com.example.springwebforms.domain.Form;
import com.example.springwebforms.domain.Question;
import com.example.springwebforms.domain.User;
import com.example.springwebforms.loggers.EventLogger;
import com.example.springwebforms.loggers.event.Event;
import com.example.springwebforms.loggers.event.EventType;
import com.example.springwebforms.repos.AnswerRepo;
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

@Controller
@RequestMapping("/formEdition")
@PreAuthorize("hasAuthority('ADMIN')")
public class FormEditionController {

    @Autowired
    private EventLogger eventLogger;


    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private FormRepo formRepo;

    @Autowired
    private AnswerRepo answerRepo;

    @GetMapping("/add")
    public String formAddPage(@AuthenticationPrincipal User user,
                              HttpServletRequest request) {
        eventLogger.logEvent(Event.level(EventType.INFO).that(request.getRemoteHost() + " creating form").now());

        var form = new Form(user, "<Без имени>", new HashSet<>());
        formRepo.save(form);

        return "redirect:/formEdition/"+form.getId();
    }

    @GetMapping("{form}")
    public String formAddPage(@PathVariable Form form,
                              Model model,
                              HttpServletRequest request) {
        eventLogger.logEvent(Event.level(EventType.INFO).that(request.getRemoteHost() + " editing form").now());

        model.addAttribute("form", form);
        model.addAttribute("formName", form.getName());
        model.addAttribute("questions", form.getQuestions());
        model.addAttribute("isActive", form.isActive());

        formRepo.save(form);

        return "formEdit";
    }

    @GetMapping("/delete/{form}")
    public String formDelete(
            @PathVariable Form form,
            HttpServletRequest request
    ) {
        eventLogger.logEvent(Event.level(EventType.INFO).that(request.getRemoteHost() + " deleted form: " + form.getId() + " " + form.getName()).now());

        var questions = form.getQuestions();
        for (var question : questions) {
            for(var answer : answerRepo.findAnswerByQuestion(question)) {
                answerRepo.delete(answer);
            }
            questionRepo.delete(question);
        }
        formRepo.delete(form);

        return "redirect:/form";
    }

    @PostMapping("{form}")
    public String formEdit(
            @PathVariable Form form,
            @RequestParam String formName,
            @RequestParam Map<String, String> questions,
            HttpServletRequest request,
            @RequestParam(required = false) boolean isActive) {
        eventLogger.logEvent(Event.level(EventType.INFO).that(request.getRemoteHost() + " edited form: " + formName).now());

        form.setName(formName);

        form.setActive(isActive);

        var questionEnts = form.getQuestions();
        for(var question : questions.keySet()) {
            if (question.contains("question")) {
                var q = questions.get(question);
                var questionEnt = questionRepo.findByQuestionAndForm(q, form);
                if (questionEnt == null) {
                    questionEnt = new Question(q, form);
                    questionRepo.save(questionEnt);
                }
                questionEnts.add(questionEnt);
            }
        }

        form.setQuestions(questionEnts);

        formRepo.save(form);

        return "redirect:/main";
    }
}
