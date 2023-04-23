package com.project.XX.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.XX.entity.Course;
import com.project.XX.entity.User;
import com.project.XX.exception.ResourceNotFoundException;
import com.project.XX.model.CourseInstructorDto;
import com.project.XX.repo.CourseRepository;
import com.project.XX.repo.UserRepository;
import com.project.XX.util.Role;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));

        existingCourse.setTitle(course.getTitle());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setInstructors(course.getInstructors());
        existingCourse.setStudents(course.getStudents());

        return courseRepository.save(existingCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));

        courseRepository.delete(course);
    }
    
    @Override
    public void addInstructorToCourse(CourseInstructorDto courseInstructorDto) throws ResourceNotFoundException {
        Course course = courseRepository.findById(courseInstructorDto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        User instructor = userRepository.findByIdAndRole(courseInstructorDto.getInstructorId(), Role.INSTRUCTOR)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));
        course.addInstructor(instructor);
        courseRepository.save(course);
    }


}

