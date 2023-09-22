package com.betrybe.agrix.controller.advice;


import com.betrybe.agrix.service.exception.CropsNotFoundException;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalControllerAdvice.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler(FarmNotFoundException.class)
  public ResponseEntity<String> handleFarmNotFound(FarmNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler(CropsNotFoundException.class)
  public ResponseEntity<String> handleCropNotFound(CropsNotFoundException exception) {
    return ResponseEntity.status((HttpStatus.NOT_FOUND)).body(exception.getMessage());
  }

  @ExceptionHandler(FertilizerNotFoundException.class)
  public ResponseEntity<String> handleFertilizerNotFound(FertilizerNotFoundException exception) {
    return ResponseEntity.status((HttpStatus.NOT_FOUND)).body(exception.getMessage());
  }
}
