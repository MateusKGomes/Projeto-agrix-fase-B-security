package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service layer class for handling persons business logic.
 */
@RequiredArgsConstructor
@Service
public class PersonService implements UserDetailsService {

  private final PersonRepository personRepository;

  /**
   * Returns a person for a given ID.
   */
  public Person getPersonById(Long id) {
    Optional<Person> person = personRepository.findById(id);

    if (person.isEmpty()) {
      throw new PersonNotFoundException();
    }

    return person.get();
  }

  /**
   * Returns a person for a given username.
   */
  public Person getPersonByUsername(String username) {
    Person person = personRepository.findByUsername(username);

    if (person == null) {
      throw new PersonNotFoundException();
    }

    return person;
  }

  /**
   * Creates a new person.
   */
  public Person create(Person person) {
    String hashedPassword = new BCryptPasswordEncoder().encode(person.getPassword());
    person.setPassword(hashedPassword);
    return personRepository.save(person);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return personRepository.findByUsername(username);
  }
}
