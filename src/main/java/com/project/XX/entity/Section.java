package com.project.XX.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "section")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String subtitle;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @OneToMany(mappedBy = "section")
    private List<Subsection> subsections;
}

