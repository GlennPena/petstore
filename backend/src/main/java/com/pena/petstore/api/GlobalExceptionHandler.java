package com.pena.petstore.api;

import com.pena.petstore.service.PetListingNotFoundException;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(PetListingNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleNotFound(PetListingNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error(HttpStatus.NOT_FOUND, exception.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException exception) {
    BindingResult bindingResult = exception.getBindingResult();
    Map<String, String> fieldErrors = bindingResult.getFieldErrors().stream()
        .collect(Collectors.toMap(error -> error.getField(), error -> error.getDefaultMessage(), (left, right) -> left,
            LinkedHashMap::new));
    Map<String, Object> body = error(HttpStatus.BAD_REQUEST, "Validation failed");
    body.put("fields", fieldErrors);
    return ResponseEntity.badRequest().body(body);
  }

  private Map<String, Object> error(HttpStatus status, String message) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("status", status.value());
    body.put("message", message);
    body.put("timestamp", Instant.now().toString());
    return body;
  }
}
