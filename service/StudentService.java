package kalush666.studentmanagment.service;

import kalush666.studentmanagment.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(Long id);
    StudentDTO addStudent(StudentDTO studentDTO);
    StudentDTO updateStudent(StudentDTO studentDTO, Long id);
    void deleteStudent(Long id);
}
