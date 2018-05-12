package com.example.tablephone.repository;

import com.example.tablephone.model.Person;
import org.springframework.data.repository.CrudRepository;


public interface PersonRepository extends CrudRepository<Person,Long> {
}
