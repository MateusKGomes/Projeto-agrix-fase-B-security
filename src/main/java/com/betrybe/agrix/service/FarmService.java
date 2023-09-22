package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crops;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.repository.CropsRepository;
import com.betrybe.agrix.repository.FarmRepository;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * FarmService.
 */
@RequiredArgsConstructor
@Service
public class FarmService {

  private final FarmRepository farmRepository;
  private final CropsRepository cropsRepository;

  public Farm saveFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  public List<Farm> findAllFarms() {
    return farmRepository.findAll();
  }

  /**
   * findFarmByID.
   */
  public Farm findFarmById(Long id) throws FarmNotFoundException {
    return farmRepository
        .findById(id)
        .orElseThrow(
            FarmNotFoundException::new
        );
  }

  /**
   * createNewCrops.
   */
  public Crops createNewCrops(Crops crops, Long id) throws FarmNotFoundException {
    Farm farm = findFarmById(id);
    crops.setFarm(farm);
    crops.setFarmId(farm.getId());
    return cropsRepository.save(crops);
  }

  /**
   * listCrops.
   */
  private List<Crops> listCrops(Long id) {

    return cropsRepository.findAll().stream()
        .filter(crop -> id.equals(crop.getFarmId())).collect(Collectors.toList());
  }


  /**
   * findCropsInFarm.
   */
  public List<Crops> findCropsInFarm(Long id) throws FarmNotFoundException {
    Farm farm = farmRepository.findById(id).orElseThrow(FarmNotFoundException::new);

    return listCrops(farm.getId());

  }

}
