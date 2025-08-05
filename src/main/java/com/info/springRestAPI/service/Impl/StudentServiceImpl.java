package com.info.springRestAPI.service.Impl;

import com.info.springRestAPI.dto.StudentDTO;
import com.info.springRestAPI.entity.Student;
import com.info.springRestAPI.repository.StudentRepository;
import com.info.springRestAPI.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students=studentRepository.findAll();
       return students
                .stream()
                .map(student->new StudentDTO(student.getId(),student.getName(),student.getEmail()))
               .toList();

    }
}
