package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropsDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.entity.Crops;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.exception.CropsNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * CropController.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  /**
   * findAllCrops.
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<CropsDto> findAllCrops() {
    List<Crops> crops = cropService.findAllCrops();

    return crops.stream()
        .map(CropsDto::fromEntity)
        .collect(Collectors.toList());

  }

  /**
   * findCropsById.
   */
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CropsDto findCropsById(@PathVariable("id") Long id) throws CropsNotFoundException {
    Crops crops = cropService.findCropsById(id);

    return CropsDto.fromEntity(crops);

  }

  /**
   * findActiveCrops.
   */
  @GetMapping("/search")
  @ResponseStatus(HttpStatus.OK)
  public List<CropsDto> findActiveCrops(
      @RequestParam LocalDate start, @RequestParam LocalDate end) {

    List<Crops> crops = cropService.findAllCrops();

    return crops.stream()
        .filter(
            crop -> (crop.getHarvestDate()
                .isAfter(start) || crop.getHarvestDate()
                .isEqual(start))
                && (crop.getHarvestDate()
                .isBefore(end) || crop.getHarvestDate()
                .isEqual(end)))
        .map(CropsDto::fromEntity)
        .collect(Collectors.toList());

  }

  /**
   * associateCropsAndFertilizer.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<String> associateCropsAndFertilizer(
      @PathVariable("cropId") Long cropsId,
      @PathVariable("fertilizerId") Long fertilizerId
  ) throws CropsNotFoundException, FertilizerNotFoundException {

    cropService.associateCropsAndFertilizer(cropsId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * getFertilizersInCrop.
   */
  @GetMapping("/{cropId}/fertilizers")
  @ResponseStatus(HttpStatus.OK)
  public List<FertilizerDto> getFertilizersInCrop(@PathVariable("cropId") Long id)
      throws CropsNotFoundException {
    Crops crops = cropService.findCropsById(id);

    List<Fertilizer> fertilizers = crops.getFertilizer();

    return fertilizers.stream()
        .map(FertilizerDto::fromEntity)
        .collect(Collectors.toList());

  }

}
