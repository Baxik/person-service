package telran.java41.person.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java41.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
