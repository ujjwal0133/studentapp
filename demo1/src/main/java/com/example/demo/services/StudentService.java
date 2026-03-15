package com.example.demo.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.repository.StudentRepo;

@Service
public class StudentService {

	private final StudentRepo repo;
	
	StudentService(StudentRepo rep){
		this.repo = rep;
	}
	
	@Autowired
	StudentMapper mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
	
	public List<StudentDto> getAll(){
		logger.info("Fetching all students.");
		
		List<Student> list =  repo.findAll();
		return list.stream()
			.map(a -> mapper.fromEntity(a))
			.toList();
		
	}
	
	public StudentDto getStudent(String r) {
		Student a =  repo.findByRoll(r);
		logger.info("Fetching student {}",a.getName());
		return mapper.fromEntity(a);
	}
	
	public void addStudent(StudentDto a) {
		repo.save(mapper.toEntity(a));
		logger.info("Adding student name {}",a.getName());
	}
	
	public List<StudentDto> search(Pageable pageable,String name,String roll){
		
		Page<Student> page = null;
		
		if(name==null && roll == null)
			page = repo.findAll(pageable);
		else if(name != null && roll == null)
			page = repo.findByNameContainingIgnoreCase(name,pageable);
		else if(name == null && roll != null)
			page = repo.findByRollContainingIgnoreCase(roll,pageable);
		else if(name != null && roll != null)
			page = repo.findByNameContainingIgnoreCaseAndRollContainingIgnoreCase(name,roll,pageable);
		 
		return page.map(mapper::fromEntity).getContent(); 
		
	}
	
}
