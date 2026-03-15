package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

	public Student toEntity(StudentDto a);
	
	public StudentDto fromEntity(Student a);
}
