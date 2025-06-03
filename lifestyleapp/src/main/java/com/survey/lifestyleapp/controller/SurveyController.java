package com.survey.lifestyleapp.controller;

import com.survey.lifestyleapp.model.SurveyResponse;
import com.survey.lifestyleapp.repository.SurveyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller  // Spring MVC Controller
public class SurveyController {

    @Autowired
    private SurveyRepository repository;  // Injects the JPA repo for DB access

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("survey", new SurveyResponse());  // Binds form to empty object
        return "form";  // Loads form.html
    }

    @PostMapping("/submit")
    public String submitSurvey(@Valid @ModelAttribute("survey") SurveyResponse survey,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "form";  // Return form with validation errors
        }
        survey.setSurveyDate(LocalDate.now());  // Set current date on submission
        repository.save(survey);  // Save to database
        return "redirect:/results";  // Redirect to results page
    }

    @GetMapping("/results")
    public String viewResults(Model model) {
        List<SurveyResponse> surveys = repository.findAll();
        if (surveys.isEmpty()) {
            model.addAttribute("message", "No Surveys Available.");
            return "results";
        }

        model.addAttribute("total", surveys.size());
        model.addAttribute("avgAge", (int) surveys.stream().mapToInt(SurveyResponse::getAge).average().orElse(0));
        model.addAttribute("minAge", surveys.stream().mapToInt(SurveyResponse::getAge).min().orElse(0));
        model.addAttribute("maxAge", surveys.stream().mapToInt(SurveyResponse::getAge).max().orElse(0));

        // Food percentages
        double total = surveys.size();
        double pizza = surveys.stream().filter(s -> s.getFavouriteFoods() != null && s.getFavouriteFoods().contains("Pizza")).count();
        double pasta = surveys.stream().filter(s -> s.getFavouriteFoods() != null && s.getFavouriteFoods().contains("Pasta")).count();
        double pap = surveys.stream().filter(s -> s.getFavouriteFoods() != null && s.getFavouriteFoods().contains("Pap and Wors")).count();

        model.addAttribute("pizzaPercent", String.format("%.1f", (pizza * 100.0 / total)));
        model.addAttribute("pastaPercent", String.format("%.1f", (pasta * 100.0 / total)));
        model.addAttribute("papPercent", String.format("%.1f", (pap * 100.0 / total)));

        // Rating averages
        model.addAttribute("avgWatchMovies", String.format("%.1f", surveys.stream().mapToInt(SurveyResponse::getRatingWatchMovies).average().orElse(0)));
        model.addAttribute("avgListenToRadio", String.format("%.1f", surveys.stream().mapToInt(SurveyResponse::getRatingListenToRadio).average().orElse(0)));
        model.addAttribute("avgEatOut", String.format("%.1f", surveys.stream().mapToInt(SurveyResponse::getRatingEatOut).average().orElse(0)));
        model.addAttribute("avgWatchTV", String.format("%.1f", surveys.stream().mapToInt(SurveyResponse::getRatingWatchTV).average().orElse(0)));

        return "results";  // Loads results.html
    }

}
