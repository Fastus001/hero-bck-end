package pl.fastus.wfrpg.hero.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends
        ResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleArgumentTypeMismatch(RuntimeException e, WebRequest request){
        String responseBody = "Argument mismatch! Should be number (Integer) between 1-4!!";
        return handleExceptionInternal(e, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstrainsViolationException(RuntimeException e, WebRequest request){
        String responseBody = e.getMessage();
        return handleExceptionInternal(e, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
