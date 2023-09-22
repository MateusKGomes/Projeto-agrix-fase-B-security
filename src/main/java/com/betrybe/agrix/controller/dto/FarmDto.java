package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Farm;

/**
 * FarmDto.
 */
public record FarmDto(Long id, String name, Double size) {

  /**
   * fromEntity.
   */
  public static FarmDto fromEntity(Farm farm) {
    return new FarmDto(
        farm.getId(),
        farm.getName(),
        farm.getSize()
    );
  }

  /**
   * toEntity.
   */
  public Farm toEntity() {
    Farm farm = new Farm();
    farm.setName(name);
    farm.setSize(size);
    return farm;
  }
}
