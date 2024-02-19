package com.jason.mirageledger.course.entity.dto;

import com.jason.mirageledger.course.entity.po.Course;
import lombok.Data;

@Data
public class CourseDTO extends Course {
    private String userId;
    private String userName;
    private String department;
}
