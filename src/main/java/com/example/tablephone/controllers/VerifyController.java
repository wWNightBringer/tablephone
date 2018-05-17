package com.example.tablephone.controllers;

import com.example.tablephone.impl.PhonebookImpl;
import com.example.tablephone.model.Autorize;
import com.example.tablephone.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VerifyController {
    private int id;
    private Autorize autorize;
    private String login;
    private String password;
    private PhonebookImpl phonebook;
    @Value("${phonebook.error.loginOrPassword}")
    private String error;

    @Autowired
    public VerifyController(Autorize autorize, PhonebookImpl phonebook) throws Exception {
        this.autorize = autorize;
        this.phonebook = phonebook;
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
