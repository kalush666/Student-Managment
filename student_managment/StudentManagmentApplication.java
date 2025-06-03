package kalush666.studentmanagment.student_managment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class StudentManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagmentApplication.class, args);
    }

}
