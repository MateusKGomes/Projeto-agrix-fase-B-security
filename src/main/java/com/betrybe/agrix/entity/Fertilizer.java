package com.betrybe.agrix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Fertilizer.
 */
@Data
@Entity
@Table(name = "fertilizer")
@AllArgsConstructor
@NoArgsConstructor
public class Fertilizer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String brand;

  private String composition;

  @ManyToMany(mappedBy = "fertilizer")
  @JsonIgnore
  private List<Crops> crops;

}


