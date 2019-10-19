package com.myfitband.server;

import com.myfitband.server.dao.UserRepository;
import com.myfitband.server.entity.User;
import com.myfitband.server.entity.mobile.LoginData;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {

    private final UserRepository userRepository;

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

    public boolean userExists(LoginData loginData) {
        return getDataOfUser(loginData).isPresent();
    }

    public void removeUser(LoginData loginData) {
        User userToRemove = getDataOfUser(loginData).orElseThrow(() -> new IllegalStateException("Cannot delete not existing user"));

        userRepository.delete(userToRemove);
    }

    private Optional<User> getDataOfUser(LoginData loginData) {
        return userRepository
                .findAll().stream()
                .filter(u -> u.getLogin().equals(loginData.getLogin()) && u.getPassword().equals(loginData.getPassword()))
                .findFirst();
    }
}
