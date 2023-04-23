package com.project.XX.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CourseDto {
    private Long id;
    private String title;
    private String description;
    private List<UserDto> instructors;
    private List<UserDto> students;
}
