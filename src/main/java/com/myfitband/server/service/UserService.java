package com.myfitband.server.service;

import com.myfitband.server.dao.UserRepository;
import com.myfitband.server.entity.User;
import com.myfitband.server.entity.mobile.LoginData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean userCanBeCreated(User user) {
        boolean userWithSameLoginOrEmailExists =
                userRepository
                        .findAll().stream()
                        .anyMatch(u -> u.getLogin().equals(user.getLogin()) || u.getEmail().equals(user.getEmail()));
        return !userWithSameLoginOrEmailExists;
    }

    public void createNewUser(User user) {
        userRepository.save(user);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public boolean userExists(LoginData loginData) {
        return getDataOfUser(loginData).isPresent();
    }

    public void removeUser(LoginData loginData) {
        User userToRemove = getDataOfUser(loginData).orElseThrow(() -> new IllegalStateException("Cannot delete not existing user"));

        userRepository.delete(userToRemove);
    }

    public Optional<User> getDataOfUser(LoginData loginData) {
        return userRepository
                .findAll().stream()
                .filter(u -> u.getLogin().equals(loginData.getLogin())
                        && passwordEncoder.matches(loginData.getPassword(), u.getPassword()))
                .findFirst();

    }

    public User findUserByUsername(String username) {
        return userRepository.findByLogin(username);
    }
}
