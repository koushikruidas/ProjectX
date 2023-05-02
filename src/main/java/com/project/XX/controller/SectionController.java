package com.project.XX.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.XX.entity.Section;
import com.project.XX.exception.ResourceNotFoundException;
import com.project.XX.repo.SectionRepository;

@RestController
@RequestMapping("/sections")
public class SectionController {

    @Autowired
    private SectionRepository sectionRepository;

    @GetMapping("/getall")
    public ResponseEntity<Page<Section>> getAllSections(@RequestParam(defaultValue = "0") int page, 
                                                         @RequestParam(defaultValue = "10") int size, 
                                                         @RequestParam(defaultValue = "id,desc") String[] sort) {
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(sort));
        Page<Section> sections = sectionRepository.findAll(pagingSort);
        return new ResponseEntity<>(sections, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Section getSectionById(@PathVariable Long id) {
        return sectionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Section", "id", id));
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Section createChapter(@Valid @RequestBody Section section) {
        return sectionRepository.save(section);
    }

    @PutMapping("/{id}")
    public Section updateChapter(@PathVariable Long id, @Valid @RequestBody Section sectionDetails) {
        Section section = sectionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Section", "id", id));
        section.setTitle(sectionDetails.getTitle());
        section.setSubtitle(sectionDetails.getSubtitle());
        section.setSubsections(sectionDetails.getSubsections());
        return sectionRepository.save(section);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChapter(@PathVariable Long id) {
        Section section = sectionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Section", "id", id));
        sectionRepository.delete(section);
        return ResponseEntity.ok().build();
    }
}
   

