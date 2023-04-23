package com.project.XX.service;

import java.util.List;

import com.project.XX.entity.Course;
import com.project.XX.exception.ResourceNotFoundException;
import com.project.XX.model.CourseInstructorDto;


public interface ICourseService {

    List<Course> getAllCourses();

    Course getCourseById(Long id);

    Course createCourse(Course course);

    Course updateCourse(Long id, Course course);

    void deleteCourse(Long id);
    
    void addInstructorToCourse(CourseInstructorDto courseInstructorDto) throws ResourceNotFoundException;
    
}

