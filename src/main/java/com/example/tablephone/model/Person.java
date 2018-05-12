package com.example.tablephone.model;

import com.example.tablephone.annotation.Form;
import org.springframework.stereotype.Service;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Service
public class Person {
    @Form(
            value = "[a-zA-Z]{1,20}.[a-zA-Z]{1,2}.[a-zA-Z]{1,2}$",
            message = "Correct fullName",
            errorMessage = "Invalid fullName"
    )
    private String fullName;
    @Form(
            value = "[a-zA-Z0-9]{1,30}",
            message = "Correct address",
            errorMessage = "Invalid address"
    )
    private String login;
    private int id;
    @Form(
            value = "[a-zA-Z0-9]{1,20}",
            message = "Correct password",
            errorMessage = "Invalid password"
    )
    private String password;

    public Person(String login, String password) {
        this.login = login;
        this.id = id;
        this.password = password;
    }

    public Person() {
    }

    @Basic
    @Column(name = "Full_name", nullable = false, length = 45)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "login", nullable = false, length = 40)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Id
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 30)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
