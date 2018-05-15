package com.example.tablephone.model;

import com.example.tablephone.annotation.Form;
import org.springframework.stereotype.Service;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Service
public class Information {
    private int id;
    @Form(
            value = "[0-9]{12}",
            message = "Correct phone",
            errorMessage = "Invalid phone"
    )
    private String phone;
    @Form(
            value = "[a-zA-Z0-9]{1,20}@[a-z]{1,10}.[a-z]{1,5}",
            message = "Correct email",
            errorMessage = "Invalid email"
    )
    private String email;
    @Form(
            value = "[a-zA-Z]{1,20}$",
            message = "Correct name",
            errorMessage = "Invalid name"
    )
    private String contactName;
    @Form(
            value = "[a-zA-Z]{1,30}$",
            message = "Correct address",
            errorMessage = "Invalid address"
    )
    private String address;
    private int personId;

    public Information( String contactName, String address, String phone, String email) {
        this.phone = phone;
        this.email = email;
        this.contactName = contactName;
        this.address = address;
    }

    public Information() {
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
    @Column(name = "phone", nullable = false, length = 45)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "contact_name", nullable = false, length = 45)
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 45)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "person_id", nullable = false)
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Information that = (Information) object;

        if (id != that.id) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (contactName != null ? !contactName.equals(that.contactName) : that.contactName != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (contactName != null ? contactName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
