package com.survey.lifestyleapp.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

@Entity  // Marks this class as a table in the H2 database (JPA entity)
public class SurveyResponse {

    @Id
    @GeneratedValue  // Auto-generates unique ID
    private int id;

    @NotBlank(message = "Full name is required")  // Validates non-empty input
    private String fullName;

    @NotBlank(message = "Email is required")
    private String email;

    @Min(value = 5, message = "Age must be at least 5") // Age validation (min)
    @Max(value = 120, message = "Age must not exceed 120") // Age validation (max)
    private int age;

    private LocalDate surveyDate;

    @Pattern(regexp = "\\d{10}", message = "Enter a valid 10-digit number")
    private String contactNumber;



    @ElementCollection
    private List<String> favouriteFoods;  // For storing multiple food values (checkboxes)

    @NotNull(message = "Select rating for eating out")
    private Integer ratingEatOut;

    @NotNull(message = "Select rating for watching movies")
    private Integer ratingWatchMovies;

    @NotNull(message = "Select rating for watching TV")
    private Integer ratingWatchTV;

    @NotNull(message = "Select rating for listening to radio")
    private Integer ratingListenToRadio;


    //Getters and Setters
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(LocalDate surveyDate) {
        this.surveyDate = surveyDate;
    }

    public List<String> getFavouriteFoods() {
        return favouriteFoods;
    }

    public void setFavouriteFoods(List<String> favouriteFoods) {
        this.favouriteFoods = favouriteFoods;
    }

    public Integer getRatingEatOut() {
        return ratingEatOut;
    }

    public void setRatingEatOut(Integer ratingEatOut) {
        this.ratingEatOut = ratingEatOut;
    }

    public Integer getRatingWatchMovies() {
        return ratingWatchMovies;
    }

    public void setRatingWatchMovies(Integer ratingWatchMovies) {
        this.ratingWatchMovies = ratingWatchMovies;
    }

    public Integer getRatingWatchTV() {
        return ratingWatchTV;
    }

    public void setRatingWatchTV(Integer ratingWatchTV) {
        this.ratingWatchTV = ratingWatchTV;
    }

    public Integer getRatingListenToRadio() {
        return ratingListenToRadio;
    }

    public void setRatingListenToRadio(Integer ratingListenToRadio) {
        this.ratingListenToRadio = ratingListenToRadio;
    }
}
