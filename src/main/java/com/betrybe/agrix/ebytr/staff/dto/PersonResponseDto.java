package com.betrybe.agrix.ebytr.staff.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * PersonResponseDto.
 */
public record PersonResponseDto(Long id, String username, Role role) {

  /**
   * fromEntity.
   */
  public static PersonResponseDto fromEntity(Person person) {
    return new PersonResponseDto(
        person.getId(),
        person.getUsername(),
        person.getRole()
    );
  }

}
