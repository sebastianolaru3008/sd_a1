package com.tabs.assignment1.service;

import com.tabs.assignment1.model.abstraction_objects.UserDAO;
import com.tabs.assignment1.model.transfer_objects.UserDTO;
import com.tabs.assignment1.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {

    private final UserRepository userRepository = new UserRepository();

    private void userDataValidation(UserDTO userData) throws Exception {
        if (userData.getUsername().isEmpty() || userData.getPassword().isEmpty())
            throw new Exception("Invalid empty fields!");
    }

    public UserDAO createUser(UserDTO userData) throws Exception {
        userDataValidation(userData);

        return userRepository.insertUser(userData);
    }

    public UserDAO loginUser(UserDTO userData) throws Exception {
        userDataValidation(userData);

        UserDAO user = userRepository.selectUser(userData);

        if (!BCrypt.checkpw(userData.getPassword(), user.getPasswordHash())) {
            throw new Exception("Wrong password for this username");
        }

        return user;
    }
}
