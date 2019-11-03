package com.myfitband.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.myfitband.server.dto.SessionUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User  implements Serializable, UserDetails {

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
    @JsonBackReference(value = "user-physicalProperties")
    private PhysicalProperties physicalProperties;

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user-workout")
    private Set<Workout> workouts;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value="user-alerts")
    private List<Alert> alertList;

    @OneToOne(mappedBy = "user")
    @JsonBackReference(value = "user-settings")
    private Setting setting;

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user-measurements")
    private List<Measurement> weightMeasurementList;
    public User(){}

    public User(Integer id){
        this.userId = id;
    }

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
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

    public List<Alert> getAlertList() {
        return alertList;
    }

    public void setAlertList(List<Alert> alertList) {
        this.alertList = alertList;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public List<Measurement> getWeightMeasurementList() {
        return weightMeasurementList;
    }

    public void setWeightMeasurementList(List<Measurement> weightMeasurementList) {
        this.weightMeasurementList = weightMeasurementList;
    }

    public SessionUser getSessionUser(){
        return new SessionUser(userId, login, password, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
