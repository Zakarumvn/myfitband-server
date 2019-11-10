package com.myfitband.server.entity.mobile;

import com.myfitband.server.entity.PhysicalProperties;
import com.myfitband.server.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.ZoneId;
import java.util.Date;

public class UserData {

    private String login;
    private String email;
    private String firstName;
    private String lastname;
    private Date birthDate;
    private String password;

    public Integer getPulseMin() {
        return pulseMin;
    }

    public void setPulseMin(Integer pulseMin) {
        this.pulseMin = pulseMin;
    }

    public Integer getPulseMax() {
        return pulseMax;
    }

    public void setPulseMax(Integer pulseMax) {
        this.pulseMax = pulseMax;
    }

    private Integer pulseMin;
    private Integer pulseMax;

    public User mapToUser() {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setLogin(login);
        user.setBirthDate(birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        return user;
    }

    public PhysicalProperties mapToPhysicalProperties(User user) {
        PhysicalProperties physicalProperties = new PhysicalProperties();
        physicalProperties.setUser(user);
        physicalProperties.setRelaxPulse(pulseMin);
        physicalProperties.setStressPulse(pulseMax);
        return physicalProperties;
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
