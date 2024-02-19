package com.jason.mirageledger.course.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.mirageledger.course.entity.dto.CourseEvaluationDTO;
import com.jason.mirageledger.course.entity.po.Course;
import com.jason.mirageledger.course.entity.po.CourseEvaluation;
import com.jason.mirageledger.course.mapper.CourseMapper;
import com.jason.mirageledger.course.service.CourseEvaluationService;
import com.jason.mirageledger.course.mapper.CourseEvaluationMapper;
import com.jason.mirageledger.user.entity.po.User;
import com.jason.mirageledger.user.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* 
* @description 针对表【mirageledger_course_evaluation(课程分析评分表)】的数据库操作Service实现
* @createDate 2024-02-14 23:40:15
*/
@Service
public class CourseEvaluationServiceImpl extends ServiceImpl<CourseEvaluationMapper, CourseEvaluation>
    implements CourseEvaluationService{

    @Autowired
    private CourseEvaluationMapper courseEvaluationMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public CourseEvaluation getCourseEvaluationByIdAndUserId(String id, String userId) {
        return courseEvaluationMapper.getCourseEvaluationByIdAndUserId(id, userId);
    }

    @Override
    public Page<CourseEvaluationDTO> getCourseEvaluationPage(Integer page, Integer rows, String courseName, String userId) {
        // 1. 查询课程评价信息
        Page<CourseEvaluation> courseEvaluationPage = courseEvaluationMapper.getCourseEvaluationPage(new Page<>(page, rows), courseName, userId);

        // 2. 批量获取课程信息
        List<String> courseIds = courseEvaluationPage.getRecords().stream()
                .map(CourseEvaluation::getCourseId)
                .distinct()
                .collect(Collectors.toList());
        Map<String, Course> courseMap = courseMapper.selectBatchIds(courseIds)
                .stream()
                .collect(Collectors.toMap(Course::getId, Function.identity()));

        // 3. 批量获取用户信息（假设Course中有teacherId字段关联到User）
        List<String> userIds = new ArrayList<>(courseMap.values()).stream()
                .map(Course::getUserId)
                .distinct()
                .collect(Collectors.toList());
        Map<String, User> userMap = userMapper.selectBatchIds(userIds)
                .stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));

        // 4. 数据转换与组装
        List<CourseEvaluationDTO> courseEvaluationDTOs = courseEvaluationPage.getRecords().stream().map(evaluation -> {
            CourseEvaluationDTO dto = new CourseEvaluationDTO();
            BeanUtils.copyProperties(evaluation, dto);

            // 设置Course相关信息
            Course course = courseMap.get(evaluation.getCourseId());
            if (course != null) {
                dto.setCourseName(course.getName());
                dto.setYear(course.getYear());
                dto.setTerm(course.getTerm());

                // 设置User相关信息
                User teacher = userMap.get(course.getUserId());
                if (teacher != null) {
                    dto.setUserId(teacher.getId());
                    dto.setUserName(teacher.getName());
                    dto.setDepartment(teacher.getDepartment());
                }
            }

            return dto;
        }).collect(Collectors.toList());

        // 构造新的分页对象返回
        Page<CourseEvaluationDTO> dtoPage = new Page<>(courseEvaluationPage.getCurrent(), courseEvaluationPage.getSize(), courseEvaluationPage.getTotal());
        dtoPage.setRecords(courseEvaluationDTOs);
        return dtoPage;
    }

}




