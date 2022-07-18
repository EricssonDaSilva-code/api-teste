package com.teste.apiteste.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TB_USER")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "userCRM")
    private List<CRM> crmList = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = false)
    private String password;
    @Column(nullable = false, unique = false)
    private String name;
    @Column(nullable = false, unique = false)
    private String surname;
    @Column(nullable = false, unique = true)
    private String mobilePhone;

    @Column(nullable = false)
    private LocalDateTime registrationDate;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<CRM> getCrmList() {
        return crmList;
    }
}
