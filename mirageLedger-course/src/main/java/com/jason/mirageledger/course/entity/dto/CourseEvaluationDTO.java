package com.jason.mirageledger.course.entity.dto;

import com.jason.mirageledger.course.entity.po.CourseEvaluation;
import lombok.Data;

@Data
public class CourseEvaluationDTO extends CourseEvaluation {
    private String courseName;
    private String userId;
    private String userName;
    private String department;
    private String year;
    private String term;
}
