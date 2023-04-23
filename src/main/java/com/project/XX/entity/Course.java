package com.project.XX.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String description;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
        name = "course_instructor",
        joinColumns = { @JoinColumn(name = "course_id") },
        inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> instructors = new HashSet<>();
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
        name = "course_student",
        joinColumns = { @JoinColumn(name = "course_id") },
        inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> students = new HashSet<>();
	
	public void addInstructor(User instructor) {
        this.instructors.add(instructor);
        instructor.getInstructorCourses().add(this);
    }

    public void removeInstructor(User instructor) {
        this.instructors.remove(instructor);
        instructor.getInstructorCourses().remove(this);
    }

    public void addStudent(User student) {
        this.students.add(student);
        student.getStudentCourses().add(this);
    }

    public void removeStudent(User student) {
        this.students.remove(student);
        student.getStudentCourses().remove(this);
    }


}
