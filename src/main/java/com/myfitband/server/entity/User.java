package com.myfitband.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    public User(){}

    public User(Integer id){
        this.userId = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    private String firstName;

    private String lastName;

    private String login;

    private String password;

    private String email;

    private LocalDateTime birthDate;

    @OneToOne(mappedBy = "user")
    private PhysicalProperties physicalProperties;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private Set<Workout> workouts;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public PhysicalProperties getPhysicalProperties() {
        return physicalProperties;
    }

    public Set<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(Set<Workout> workouts) {
        this.workouts = workouts;
    }

    public void setPhysicalProperties(PhysicalProperties physicalProperties) {
        this.physicalProperties = physicalProperties;


    }


}
