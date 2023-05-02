package com.project.XX.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subsection")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Subsection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String optionType;

    private String option;

    private String hint;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
}

