package com.example.tablephone.repository;

import com.example.tablephone.model.Information;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<Information,Long> {
}
