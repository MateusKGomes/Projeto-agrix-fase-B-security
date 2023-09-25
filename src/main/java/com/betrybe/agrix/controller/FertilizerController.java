package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * FertilizerController.
 */
@RequiredArgsConstructor
@RestController()
@RequestMapping("/fertilizers")

public class FertilizerController {

  private final FertilizerService fertilizerService;

  /**
   * createNewFertilizer.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FertilizerDto createNewFertilizer(@RequestBody FertilizerDto fertilizer) {

    Fertilizer newFertilizer = fertilizerService.saveNewFertilizer(fertilizer.toEntity());
    return FertilizerDto.fromEntity(newFertilizer);

  }

  /**
   * getAllFertilizers.
   */
  @GetMapping
  @Secured("ROLE_ADMIN")
  @ResponseStatus(HttpStatus.OK)
  public List<FertilizerDto> getAllFertilizers() {

    List<Fertilizer> fertilizers = fertilizerService.getAllFertilizers();

    return fertilizers.stream()
        .map(FertilizerDto::fromEntity)
        .collect(Collectors.toList());
  }

  /**
   * findFertilizerById.
   */
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public FertilizerDto findFertilizerById(@PathVariable("id") Long id)
      throws FertilizerNotFoundException {
    Fertilizer fertilizer = fertilizerService.findFertilizerById(id);

    return FertilizerDto.fromEntity(fertilizer);

  }

}
