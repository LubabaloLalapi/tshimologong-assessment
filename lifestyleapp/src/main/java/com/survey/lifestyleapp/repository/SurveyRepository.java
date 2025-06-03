package com.survey.lifestyleapp.repository;

import com.survey.lifestyleapp.model.SurveyResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<SurveyResponse, Long>
{
}
