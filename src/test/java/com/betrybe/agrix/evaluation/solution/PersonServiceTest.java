package com.betrybe.agrix.evaluation.solution;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.security.Role;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;


@SpringBootTest
@ActiveProfiles("test")
public class PersonServiceTest {

  @Autowired
  PersonService personService;

  @MockBean
  PersonRepository personRepository;

  @Test
  public void testUserCreation() {

    Person newPersonExpected = new Person();
    newPersonExpected.setId(1L);
    newPersonExpected.setUsername("Teste Name");
    newPersonExpected.setPassword("Teste senha");
    newPersonExpected.setRole(Role.USER);

    Mockito.when(personRepository.save(any(Person.class))).thenReturn(newPersonExpected);

    Person newPerson = new Person();
    newPerson.setUsername("Teste Name");
    newPerson.setPassword("Teste senha");
    newPerson.setRole(Role.USER);

    Person personAdd = personService.create(newPerson);

    Assert.notNull(personAdd.getId(), "Person can´t be null!");
  }


  @Test
  public void testFindPersonById() {

    Person newPersonExpected = new Person();
    newPersonExpected.setId(1L);
    newPersonExpected.setUsername("Teste Name");
    newPersonExpected.setPassword("Teste senha");
    newPersonExpected.setRole(Role.USER);

    Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(newPersonExpected));

    Person personReturned = personService.getPersonById(1L);

    Mockito.verify(personRepository).findById(eq(1L));

    Assert.notNull(personReturned.getId(), "Id is required");


  }

  @Test
  public void testNotFoundPersonById() {
    Mockito.when(personRepository.findById(any())).thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(123L));

    Mockito.verify(personRepository).findById(eq(123L));

  }

  @Test
  public void testFindPersonByName() {

    Person newPersonExpected = new Person();
    newPersonExpected.setId(1L);
    newPersonExpected.setUsername("Teste Name");
    newPersonExpected.setPassword("Teste senha");
    newPersonExpected.setRole(Role.USER);

    Mockito.when(personRepository.findByUsername("Teste Name"))
        .thenReturn(newPersonExpected);

    Person person = new Person();
    person.setUsername("Teste Name");
    person.setPassword("Teste senha");
    person.setRole(Role.USER);

    Person personReturned = personService.getPersonByUsername("Teste Name");

    assertEquals(personReturned.getUsername(), person.getUsername());


  }

}
