package com.betrybe.agrix.repository;

import com.betrybe.agrix.entity.Crops;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CropsRepository.
 */
public interface CropsRepository extends JpaRepository<Crops, Long> {

}
