package com.example.tablephone.impl;

import com.example.tablephone.model.Information;
import com.example.tablephone.model.Person;
import com.example.tablephone.repository.PersonRepository;
import com.example.tablephone.repository.PhoneRepository;
import com.example.tablephone.repository.PhonebookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PhonebookImpl implements PhonebookDAO {
    private PersonRepository personRepository;
    private PhoneRepository phoneRepository;
    private List<Person> personList;
    private List<Information> phoneList;

    @Autowired
    public PhonebookImpl(PersonRepository personRepository, PhoneRepository phoneRepository) {
        this.personRepository = personRepository;
        this.phoneRepository = phoneRepository;
        personList = new ArrayList<>();
        phoneList = new ArrayList<>();
    }

    public PhonebookImpl() {

    }

    @Override
    public List<Person> getAllPerson() {
        Iterable<Person> iterator = personRepository.findAll();
        for (Iterator<Person> p = iterator.iterator(); p.hasNext();) {
            Person person=p.next();
            personList.add(person);
        }
        return personList;
    }

    @Override
    public Person getPersonById(long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.get();
    }

    @Override
    public void addPersonByTable(Person person) {
        personRepository.save(person);
    }

    @Override
    public void deletePersonByTable(Person person) {
        personRepository.delete(person);
    }

    @Override
    public void updatePerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public List<Information> getAllPhone() {
        Iterable<Information> iterator = phoneRepository.findAll();
        for (Iterator<Information> p = iterator.iterator(); p.hasNext();) {
            Information information=p.next();
            phoneList.add(information);
        }
        return phoneList;
    }

    @Override
    public Information getPhoneById(long id) {
        Optional<Information> phone = phoneRepository.findById(id);
        return phone.get();
    }

    @Override
    public void addPhoneByTable(Information phone) {
        phoneRepository.save(phone);
    }

    @Override
    public void deletePhoneByTable(Information phone) {
        phoneRepository.delete(phone);
    }

    @Override
    public void updatePhone(Information phone) {
        phoneRepository.save(phone);
    }
}
