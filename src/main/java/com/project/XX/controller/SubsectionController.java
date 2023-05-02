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

import com.project.XX.entity.Subsection;
import com.project.XX.exception.ResourceNotFoundException;
import com.project.XX.repo.SubsectionRepository;

@RestController
@RequestMapping("/subsections")
public class SubsectionController {

	@Autowired
	private SubsectionRepository subsectionRepository;

	@GetMapping("/getall")
	public ResponseEntity<Page<Subsection>> getAllCourses(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id,desc") String[] sort) {
		Pageable pagingSort = PageRequest.of(page, size, Sort.by(sort));
		Page<Subsection> subsections = subsectionRepository.findAll(pagingSort);
		return ResponseEntity.ok(subsections);
	}

	@GetMapping("/{id}")
	public Subsection getSubsectionById(@PathVariable Long id) {
		return subsectionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Subsection", "id", id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Subsection createSubsection(@Valid @RequestBody Subsection subsection) {
		return subsectionRepository.save(subsection);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Subsection> updateSubsection(@PathVariable(value = "id") Long subsectionId,
			@RequestBody Subsection subsectionDetails) throws ResourceNotFoundException {
		Subsection subsection = subsectionRepository.findById(subsectionId).orElseThrow(
				() -> new ResourceNotFoundException("Subsection not found for this id :: " + subsectionId));

		// update the subsection fields
		subsection.setContent(subsectionDetails.getContent());
		subsection.setType(subsectionDetails.getType());
		subsection.setOptionType(subsectionDetails.getOptionType());
		subsection.setOption(subsectionDetails.getOption());
		subsection.setHint(subsectionDetails.getHint());
		
		return ResponseEntity.ok(subsectionRepository.save(subsection));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSubsection(@PathVariable Long id) {
		Subsection Subsection = subsectionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Subsection", "id", id));
		subsectionRepository.delete(Subsection);
		return ResponseEntity.ok().build();
	}
}
