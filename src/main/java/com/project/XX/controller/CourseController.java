package com.project.XX.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.XX.entity.Course;
import com.project.XX.entity.User;
import com.project.XX.exception.ResourceNotFoundException;
import com.project.XX.model.CourseDto;
import com.project.XX.model.CourseInstructorDto;
import com.project.XX.model.CourseRequest;
import com.project.XX.model.UserDto;
import com.project.XX.service.CourseService;
import com.project.XX.service.UserService;
import com.project.XX.util.Role;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    public Course createCourse(@RequestBody CourseRequest courseReq) {
    	Set<User> users = new HashSet<>();
    	users = userService.getUserByIdAndRole(courseReq.getInstructors(), Role.INSTRUCTOR);
    	Course course = Course.builder()
    			.title(courseReq.getTitle())
    			.description(courseReq.getDescription())
    			.instructors(users)
    			.build();
        return courseService.createCourse(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
    
    @PostMapping("/{courseId}/enroll")
    public ResponseEntity<CourseDto> enrollInCourse(@PathVariable Long courseId, @RequestParam Long studentId) {
        Course course = courseService.getCourseById(courseId);
        User student = userService.getUserById(studentId);
        course.getStudents().add(student);
        courseService.updateCourse(course.getId(), course);
        CourseDto updatedCourseDto = CourseDto.builder()
        							.id(course.getId())
        							.title(course.getTitle())
        							.description(course.getDescription())
        							.students(Arrays.asList(modelMapper.map(student, UserDto.class)))
        							.build();
        return ResponseEntity.ok(updatedCourseDto);
    }
    
    @PostMapping("/instructors")
    public ResponseEntity<?> addInstructorToCourse(@RequestBody CourseInstructorDto courseInstructorDto) {
        try {
            courseService.addInstructorToCourse(courseInstructorDto);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
