package com.betrybe.agrix.service.exception;

/**
 * FarmNotFoundException.
 */
public class FarmNotFoundException extends NotFoundException {

  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }
}
