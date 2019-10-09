package com.myfitband.server.entity.mobile;

import com.myfitband.server.entity.User;

import java.time.ZoneId;
import java.util.Date;

public class UserData {

    private String login;
    private String email;
    private String firstName;
    private String lastname;
    private Date birthDate;
    private String password;

    public User mapToUser() {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setLogin(login);
        user.setBirthDate(birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        user.setPassword(password);
        return user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}