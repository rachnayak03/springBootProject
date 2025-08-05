package com.info.springRestAPI.service;

import com.info.springRestAPI.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();
}
