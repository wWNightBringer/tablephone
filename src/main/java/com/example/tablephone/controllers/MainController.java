package com.example.tablephone.controllers;

import com.example.tablephone.impl.PhonebookImpl;
import com.example.tablephone.model.Autorize;
import com.example.tablephone.model.Information;
import com.example.tablephone.model.Person;
import com.example.tablephone.validate.ValidateObject;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private VerifyController verifyController;
    private PhonebookImpl phonebook;
    private List<Information> phoneList;
    private Person person;
    private Information information;
    @Value("${phonebook.error.errorAddToDatabase}")
    private String error;
    private ValidateObject validateObject;


    @Autowired
    public MainController(PhonebookImpl phonebook, Person person, Information information, ValidateObject validateObject,
                          VerifyController verifyController) {
        this.phonebook = phonebook;
        this.person = person;
        this.information = information;
        this.validateObject = validateObject;
        this.verifyController = verifyController;
    }


    @GetMapping("/phonebook")
    public String phonebook(Model model) {
        phoneList = new ArrayList<>();
        for (Information s : phonebook.getAllPhone()) {
            if (s.getPersonId() == verifyController.getId()) {
                phoneList.add(new Information(s.getContactName(), s.getAddress(), s.getPhone(), s.getEmail()));
            }
        }
        model.addAttribute("information", phoneList);
        return "phonebook";
    }

    @GetMapping("/verify/registration")
    public String registration(Model model) {
        model.addAttribute("person", person);
        return "registration";
    }

    @PostMapping("/verify/registration")
    public String registrationPost(@ModelAttribute Person person, Model model) throws IllegalAccessException {
        if (!validateObject.checkForm(person)) {
            phonebook.addPersonByTable(person);
            return "redirect:/phonebook";
        }
        model.addAttribute("error", error);
        return "registration";
    }

    @GetMapping("/phonebook/add")
    public String addToDatabase(Model model) {
        model.addAttribute("information", information);
        return "add";
    }

    @PostMapping("/phonebook/add")
    public String addToDatabasePost(@ModelAttribute Information information, Model model) throws IllegalAccessException {
        if (!validateObject.checkForm(information)) {
            information.setPersonId(verifyController.getId());
            phonebook.addPhoneByTable(information);
            return "redirect:/phonebook";
        }
        model.addAttribute("error", error);
        return "add";
    }
/*
    @GetMapping("/phonebook/update")
    public String updateDatabase(Model model) {
        model.addAttribute("information", information);
        return "update";
    }

    @PostMapping("/phonebook/update")
    public String updateDatabasePost(@ModelAttribute Information information, Model model) throws IllegalAccessException {
        if (!validateObject.checkForm(information)) {
            for(Information inf:phonebook.getAllPhone()){
                if(information.getPhone().equals(inf.getPhone()) || information.getEmail().equals(inf.getEmail())){
                    phonebook.updatePhone(information);
                    return "redirect:/phonebook";
                }
            }
        }
        model.addAttribute("error", error);
        return "update";
    }*/

    @GetMapping("/phonebook/delete")
    public String deleteContact(Model model) {
        model.addAttribute("information", information);
        return "delete";
    }

    @PostMapping("/phonebook/delete")
    public String deleteContactPost(@ModelAttribute Information information, Model model) throws IllegalAccessException {
        if (!validateObject.checkForm(information)) {
            phonebook.deletePhoneByTable(information.getPhone());
            return "redirect:/phonebook";
        }
        model.addAttribute("error", error);
        return "delete";
    }


}
