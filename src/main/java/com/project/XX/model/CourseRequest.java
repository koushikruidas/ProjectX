package com.project.XX.model;

import java.util.List;

import lombok.Data;

@Data
public class CourseRequest {
    private Long id;
    private String title;
    private String description;
    private List<Long> instructors;
}
