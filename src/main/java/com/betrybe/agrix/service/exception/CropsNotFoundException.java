package com.betrybe.agrix.service.exception;

/**
 * CropsNotFoundException.
 */
public class CropsNotFoundException extends NotFoundException {

  public CropsNotFoundException() {
    super("Plantação não encontrada!");
  }
}
