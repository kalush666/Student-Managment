package kalush666.studentmanagment.exception;

import kalush666.studentmanagment.response.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotExists.class)
    public ResponseEntity<StandardResponse> handle(NotExists ex, WebRequest request) {
        Map<String,String> details = new HashMap<>();
        details.put("type","Resource Not Found");
        details.put("message", ex.getMessage());

        StandardResponse response = new StandardResponse("error",null,details);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExists.class)
    public ResponseEntity<StandardResponse> handle(AlreadyExists ex, WebRequest request) {
        Map<String,String> details = new HashMap<>();
        details.put("type","Resource Already Exists");
        details.put("message", ex.getMessage());

        StandardResponse response = new StandardResponse("error",null,details);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(StudentIdAndIdMismatch.class)
    public ResponseEntity<StandardResponse> handle(StudentIdAndIdMismatch ex, WebRequest request) {
        Map<String,String> details = new HashMap<>();
        details.put("type","Student ID Mismatch");
        details.put("message", ex.getMessage());

        StandardResponse response = new StandardResponse("error",null,details);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String,String> details = new HashMap<>();
        details.put("type","Validation Error");
        details.put("message", "Invalid input data");

        StandardResponse response = new StandardResponse("error",null,details);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponse> handleGeneralException(Exception ex, WebRequest request) {
        Map<String,String> details = new HashMap<>();
        details.put("type","Internal Server Error");
        details.put("message", ex.getMessage());

        StandardResponse response = new StandardResponse("error",null,details);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
