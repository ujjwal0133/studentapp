package com.example.demo.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.StudentDto;
import com.example.demo.services.StudentService;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService service;
	
//	@GetMapping
//	public ResponseEntity<List<StudentDto>> getAll(){
//		return ResponseEntity.ok(service.getAll());
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentDto> getStudent(@PathVariable String id) {
		return ResponseEntity.ok(service.getStudent(id));
	}
	
	@PostMapping
	public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto a) {
		StudentDto s = a;
		service.addStudent(a);
		return new ResponseEntity<>(s,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<StudentDto>> getAll(@RequestParam(defaultValue = "1",required = false)int st,
												   @RequestParam(defaultValue = "5",required = false)int sz,
												   @RequestParam(required = false)String name,
												   @RequestParam(required = false)String roll){
		
		Pageable pageable = PageRequest.of(st-1, sz);
		return ResponseEntity.ok(service.search(pageable, name, roll));
	}
	
	
}
