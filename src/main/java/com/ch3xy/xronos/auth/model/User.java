package com.ch3xy.xronos.auth.model;

import com.ch3xy.xronos.common.AbstractDomainObject;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class User extends AbstractDomainObject {

    private String username;

    private String name;

    private String surname;

    private String encryptedPassword;

    @Transient
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
