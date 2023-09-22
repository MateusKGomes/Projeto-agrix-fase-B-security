package com.betrybe.agrix.controller.dto;


import com.betrybe.agrix.entity.Crops;
import java.time.LocalDate;

/**
 * CropsDto.
 */
public record CropsDto(Long id,
                       String name,
                       Double plantedArea,
                       LocalDate plantedDate,
                       LocalDate harvestDate,
                       Long farmId) {

  /**
   * fromEntity.
   */
  public static CropsDto fromEntity(Crops crops) {
    return new CropsDto(
        crops.getId(),
        crops.getName(),
        crops.getPlantedArea(),
        crops.getPlantedDate(),
        crops.getHarvestDate(),
        crops.getFarmId()
    );
  }

  /**
   * toEntity.
   */
  public Crops toEntity() {

    Crops crops = new Crops();
    crops.setName(name);
    crops.setPlantedArea(plantedArea);
    crops.setPlantedDate(plantedDate);
    crops.setHarvestDate(harvestDate);
    return crops;
  }

}
