package com.betrybe.agrix.ebytr.staff.controller;


import com.betrybe.agrix.ebytr.staff.dto.PersonDto;
import com.betrybe.agrix.ebytr.staff.dto.PersonResponseDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PersonResponseDto savePerson(@RequestBody Person person) {
    Person newPerson = personService.create(person);

    return PersonResponseDto.fromEntity(newPerson);

  }

}
