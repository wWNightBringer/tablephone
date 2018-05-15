package com.example.tablephone.repository;

import com.example.tablephone.model.Information;
import com.example.tablephone.model.Person;



public interface PhonebookDAO {
    Iterable<Person> getAllPerson();
    Person getPersonById(long id);
    void addPersonByTable(Person person);
    void deletePersonByTable(Person person);
    void updatePerson(Person person);
    Iterable<Information> getAllPhone();
    Information getPhoneById(long id);
    void addPhoneByTable(Information information);
    void deletePhoneByTable(String phone);
    void updatePhone(Information information);
}
