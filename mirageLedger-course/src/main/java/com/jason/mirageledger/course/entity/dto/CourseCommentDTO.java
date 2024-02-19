package com.jason.mirageledger.course.entity.dto;

import com.jason.mirageledger.course.entity.po.CourseComment;
import lombok.Data;

@Data
public class CourseCommentDTO extends CourseComment {
    private String courseName;
    private String userId;
    private String userName;
    private String department;
}
