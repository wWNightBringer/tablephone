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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VerifyController {
    private Autorize autorize;
    private PhonebookImpl phonebook;
    private List<Information> phoneList;
    private Person person;
    private Information information;
    private String login;
    private String password;
    private ValidateObject validateObject;
    private int id;
    @Value("${phonebook.error.loginOrPassword}")
    private String error;

    @Autowired
    public VerifyController(Autorize autorize, PhonebookImpl phonebook, Person person, Information information, ValidateObject validateObject) {
        this.autorize = autorize;
        this.phonebook = phonebook;
        this.person = person;
        this.information = information;
        this.validateObject = validateObject;
    }


    @GetMapping("/phonebook")
    public String phonebook(Model model) {
        int i = 0;
        phoneList = new ArrayList<>();
        for (Information s : phonebook.getAllPhone()) {
            ++i;
            if (s.getPersonId() == getId()) {
                phoneList.add(new Information(s.getContactName(), s.getAddress(), s.getPhone(), s.getEmail()));
            }
        }
        System.out.println(i);
        model.addAttribute("information", phoneList);
        return "phonebook";
    }

    @GetMapping("/verify")
    public String verify(Model model) {
        model.addAttribute("autorize", autorize);
        return "verify";
    }

    @PostMapping("/verify")
    public String postVerify(@ModelAttribute Autorize autorize, Model model) {
        login = autorize.getLogin();
        password = autorize.getPassword();
        for (Person person : phonebook.getAllPerson()) {
            if (person.getLogin().equalsIgnoreCase(login) && person.getPassword().equalsIgnoreCase(password)) {
                setId(person.getId());
                return "redirect:/phonebook";
            }
        }
        model.addAttribute("error", error);
        return "verify";
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
        return "add";
    }

    @PostMapping("phonebook/add")
    public String addToDatabasePost(Model model) {
        return "add";
    }

    @GetMapping("/phonebook/update")
    public String updateDatabase(Model model) {
        return "update";
    }

    @PostMapping("phonebook/update")
    public String updateDatabasePost(Model model) {
        return "update";
    }

    @GetMapping("/phonebook/delete")
    public String deleteContact(Model model) {
        return "delete";
    }

    @PostMapping("phonebook/delete")
    public String deleteContactPost(Model model) {
        return "delete";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
