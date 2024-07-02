package org.application.kingphobe.service;

import org.application.kingphobe.dto.RegisterDTO;
import org.application.kingphobe.dto.UpdateDTO;
import org.application.kingphobe.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(int id);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByUsername(String username);

    User createUser(RegisterDTO user);

    Optional<User> updateUser(Integer id, UpdateDTO updateDTO);

    void deleteUser(int id);
}
