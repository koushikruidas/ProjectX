package com.project.XX.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.XX.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "instructors")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Course> instructorCourses = new HashSet<>();

    @ManyToMany(mappedBy = "students")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Course> studentCourses = new HashSet<>();
}
