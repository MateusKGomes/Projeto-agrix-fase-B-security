package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropsDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.entity.Crops;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * FarmController.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;

  private final CropService cropService;

  /**
   * saveFarm.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FarmDto saveFarm(@RequestBody FarmDto farm) {

    Farm newFarm = farmService.saveFarm(farm.toEntity());
    return FarmDto.fromEntity(newFarm);

  }

  /**
   * findAllFarms.
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<FarmDto> findAllFarms() {
    List<Farm> farms = farmService.findAllFarms();
    return farms.stream()
        .map(FarmDto::fromEntity)
        .toList();
  }

  /**
   * findFarmByid.
   */
  @GetMapping("/{id}")
  public FarmDto findFarmByid(@PathVariable("id") Long id) throws FarmNotFoundException {
    Farm farm = farmService.findFarmById(id);
    return FarmDto.fromEntity(farm);
  }

  /**
   * saveCrops.
   */
  @PostMapping("/{id}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  public CropsDto saveCrops(@PathVariable("id") Long id, @RequestBody CropsDto crops)
      throws FarmNotFoundException {

    Crops newCrops = farmService.createNewCrops(crops.toEntity(), id);
    return CropsDto.fromEntity(newCrops);

  }

  /**
   * findCropsInFarm.
   */
  @GetMapping("/{id}/crops")
  @ResponseStatus(HttpStatus.OK)
  public List<CropsDto> findCropsInFarm(@PathVariable("id") Long id) throws FarmNotFoundException {
    List<Crops> plantation = farmService.findCropsInFarm(id);

    return plantation.stream()
        .map(CropsDto::fromEntity)
        .toList();

  }

}
