package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer>{

	public Student findByRoll(String r);

	public Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);

	public Page<Student> findByRollContainingIgnoreCase(String roll, Pageable pageable);

	public Page<Student> findByNameContainingIgnoreCaseAndRollContainingIgnoreCase(String name, String roll, Pageable pageable);
 
 
		
}
