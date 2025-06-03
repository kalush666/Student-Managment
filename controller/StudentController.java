package kalush666.studentmanagment.controller;

import kalush666.studentmanagment.dto.StudentDTO;
import kalush666.studentmanagment.response.StandardResponse;
import kalush666.studentmanagment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllStudents(){
        List<StudentDTO> students = service.getAllStudents();
        StandardResponse response = new StandardResponse("success", students, null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getStudentById(@PathVariable int id){
        StudentDTO student = service.getStudentById((long) id);
        StandardResponse response = new StandardResponse("success", student, null);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<StandardResponse> addStudent(@RequestBody StudentDTO studentDTO){
        StudentDTO added = service.addStudent(studentDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(added.getId())
                .toUri();

        StandardResponse response = new StandardResponse("success", added, null);
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable int id){
        StudentDTO updated = service.updateStudent(studentDTO, (long) id);
        StandardResponse response = new StandardResponse("success", updated, null);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable int id){
        service.deleteStudent((long) id);
    }
}
