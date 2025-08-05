package com.info.springRestAPI.controller;

import com.info.springRestAPI.dto.StudentDTO;
import com.info.springRestAPI.entity.Student;
import com.info.springRestAPI.repository.StudentRepository;
import com.info.springRestAPI.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;


    @GetMapping("/students")
    public List<StudentDTO> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public StudentDTO getStudentById(@PathVariable Long id){
        return new StudentDTO(4L,"Harish","harish@gmail.com");
    }
}
