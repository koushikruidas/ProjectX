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

import com.project.XX.entity.Chapter;
import com.project.XX.exception.ResourceNotFoundException;
import com.project.XX.repo.ChapterRepository;

@RestController
@RequestMapping("/chapters")
public class ChapterController {

	@Autowired
	private ChapterRepository chapterRepository;

	@GetMapping("/getall")
	public ResponseEntity<Page<Chapter>> getAllCourses(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id,desc") String[] sort) {
		Pageable pagingSort = PageRequest.of(page, size, Sort.by(sort));
		Page<Chapter> chapters = chapterRepository.findAll(pagingSort);
		return new ResponseEntity<>(chapters, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public Chapter getChapterById(@PathVariable Long id) {
		return chapterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Chapter", "id", id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Chapter createChapter(@Valid @RequestBody Chapter chapter) {
		return chapterRepository.save(chapter);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Chapter> updateChapter(@PathVariable Long id, @Valid @RequestBody Chapter chapterDetails) {
		Chapter chapter = chapterRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Chapter", "id", id));
		chapter.setTitle(chapterDetails.getTitle());
		chapter.setSubtitle(chapterDetails.getSubtitle());
		chapter.setSections(chapterDetails.getSections());
		return ResponseEntity.ok(chapterRepository.save(chapter));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteChapter(@PathVariable Long id) {
		Chapter chapter = chapterRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Chapter", "id", id));
		chapterRepository.delete(chapter);
		return ResponseEntity.ok().build();
	}
}
