package com.backend.wishlist.config;

import com.backend.wishlist.exception.CustomDataNotFoundException;
import com.backend.wishlist.exception.CustomDataRuntimeExceptionException;
import com.backend.wishlist.exception.ErrorResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationException(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(CustomDataRuntimeExceptionException.class)
  public ResponseEntity<ErrorResponse> handleCustomRuntimeExceptionExceptions(
      Exception e
  ) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500

    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    e.printStackTrace(printWriter);

    return new ResponseEntity<>(
        new ErrorResponse(
            status,
            e.getMessage()
        ),
        status
    );
  }

  @ExceptionHandler(CustomDataNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleCustomNotFoundExceptionExceptions(
      Exception e
  ) {
    HttpStatus status = HttpStatus.NOT_FOUND; // 404

    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    e.printStackTrace(printWriter);

    return new ResponseEntity<>(
        new ErrorResponse(
            status,
            e.getMessage()
        ),
        status
    );
  }
}
