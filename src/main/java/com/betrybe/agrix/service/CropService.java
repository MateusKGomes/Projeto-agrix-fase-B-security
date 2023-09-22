package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crops;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.repository.CropsRepository;
import com.betrybe.agrix.repository.FertilizerRepository;
import com.betrybe.agrix.service.exception.CropsNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * CropService.
 */
@RequiredArgsConstructor
@Service
public class CropService {

  private final CropsRepository cropsRepository;

  private final FertilizerRepository fertilizerRepository;

  public List<Crops> findAllCrops() {
    return cropsRepository.findAll();
  }

  /**
   * findCropsById.
   */
  public Crops findCropsById(Long id) throws CropsNotFoundException {
    return cropsRepository
        .findById(id)
        .orElseThrow(
            CropsNotFoundException::new
        );
  }

  /**
   * AssociateCropsAndFertilizer.
   */
  public void associateCropsAndFertilizer(Long cropId, Long fertilizerId)
      throws CropsNotFoundException, FertilizerNotFoundException {
    Fertilizer fertilizer = fertilizerRepository.findById(fertilizerId)
        .orElseThrow(FertilizerNotFoundException::new);
    Crops crops = cropsRepository.findById(cropId)
        .orElseThrow(CropsNotFoundException::new);

    crops.getFertilizer().add(fertilizer);

    cropsRepository.save(crops);
  }


}
