package com.info.springRestAPI.service.Impl;

import com.info.springRestAPI.dto.AddStudentRequestDTO;
import com.info.springRestAPI.dto.StudentDTO;
import com.info.springRestAPI.entity.Student;
import com.info.springRestAPI.repository.StudentRepository;
import com.info.springRestAPI.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students=studentRepository.findAll();
       return students
                .stream()
                .map(student->modelMapper.map(student,StudentDTO.class))
               .toList();

    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student=studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found with id:"+id));
        StudentDTO studentDTO=modelMapper.map(student,StudentDTO.class);
        return studentDTO;
    }

    @Override
    public StudentDTO createNewStudent(AddStudentRequestDTO addStudentRequestDTO) {
        Student newStudent=modelMapper.map(addStudentRequestDTO,Student.class);
        Student student=studentRepository.save(newStudent);

        return modelMapper.map(student,StudentDTO.class) ;
    }

    @Override
    public void deleteStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new IllegalArgumentException("Student does not exist" + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO updateStudent(Long id, AddStudentRequestDTO addStudentRequestDTO) {
        Student student=studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found with id:"+id));
        modelMapper.map(addStudentRequestDTO,student);
        student=studentRepository.save(student);
        return modelMapper.map(student,StudentDTO.class);
    }

    @Override
    public StudentDTO updatePartialStudent(Long id, Map<String, Object> updates) {
        Student student=studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found with id:"+id));
        updates.forEach((field,value)->{
            switch(field){
                case "name":
                    student.setName((String) value);
                    break;
                case "email":
                    student.setEmail((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Field is not supported");
            }
        });
        Student savedstudent=studentRepository.save(student);
        return modelMapper.map(savedstudent,StudentDTO.class);
    }

}
