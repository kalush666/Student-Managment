package kalush666.studentmanagment.service;

import kalush666.studentmanagment.dto.StudentDTO;
import kalush666.studentmanagment.entity.Student;
import kalush666.studentmanagment.exception.AlreadyExists;
import kalush666.studentmanagment.exception.NotExists;
import kalush666.studentmanagment.exception.StudentIdAndIdMismatch;
import kalush666.studentmanagment.mapper.StudentMapper;
import kalush666.studentmanagment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{
    private final StudentRepository repository;
    private final StudentMapper mapper;

    @Autowired
    public StudentServiceImpl(StudentRepository repository, StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> getAllStudents() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDTO getStudentById(Long id) {
        Student student = repository.findById(id)
                .orElseThrow(()-> new NotExists("Student with ID " + id + " does not exist"));
        return mapper.toDto(student);
    }

    @Override
    @Transactional
    public StudentDTO addStudent(StudentDTO studentDTO) {
        if (repository.findByEmail(studentDTO.getEmail()).isPresent()) {
            throw new AlreadyExists("Student with email " + studentDTO.getEmail() + " already exists");
        }
        Student student = mapper.toEntity(studentDTO);
        Student savedStudent = repository.save(student);
        return mapper.toDto(savedStudent);
    }

    @Override
    @Transactional
    public StudentDTO updateStudent(StudentDTO studentDTO, Long id) {
        if (!id.equals(studentDTO.getId())) {
            throw new StudentIdAndIdMismatch("Student ID in path and body do not match");
        }

        Student existingStudent = repository.findById(id)
                .orElseThrow(() -> new NotExists("Student with ID " + id + " does not exist"));

        if (repository.findByEmail(studentDTO.getEmail()).isPresent() &&
                !repository.findByEmail(studentDTO.getEmail()).get().getId().equals(id)) {
            throw new AlreadyExists("Student with email " + studentDTO.getEmail() + " already exists");
        }

        mapper.updateEntityFromDto(studentDTO, existingStudent);
        Student updatedStudent = repository.save(existingStudent);
        return mapper.toDto(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!repository.existsById(id)) {
            throw new NotExists("Student with ID " + id + " does not exist");
        }
        repository.deleteById(id);
    }
}
