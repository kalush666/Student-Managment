package kalush666.studentmanagment.utils;

import kalush666.studentmanagment.entity.Student;
import kalush666.studentmanagment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final StudentRepository repository;

    @Autowired
    public DataInitializer(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            repository.save(new Student(null, "John", "Doe", 21.5,"jhon.doe@example.com"));
            repository.save(new Student(null, "Jane", "Smith", 22.3, "jane.smith@example.com"));
            repository.save(new Student(null, "Alice", "Johnson", 20.7, "alice.johnson@example.com"));
            repository.save(new Student(null, "Bob", "Brown", 23.1, "bob.brown@example.com"));
            repository.save(new Student(null, "Charlie", "Davis", 22.8, "charlie.davis@example.com"));
            System.out.println("Sample data initialized.");
        } else {
            System.out.println("Sample data already exists, skipping initialization.");
        }
    }
}
