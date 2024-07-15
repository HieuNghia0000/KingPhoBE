package org.application.kingphobe.service.impl;

import org.application.kingphobe.dto.AvatarUpdateDTO;
import org.application.kingphobe.dto.RegisterDTO;
import org.application.kingphobe.dto.UpdateDTO;
import org.application.kingphobe.model.User;
import org.application.kingphobe.repository.UserRepository;
import org.application.kingphobe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findByUserId(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User createUser(RegisterDTO user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(encoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setPhone(user.getPhone());
        newUser.setFullname(user.getFullname());

        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> updateUser(Integer id, UpdateDTO updateDTO) {
        Optional<User> existingUser = getUserById(id);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(updateDTO.getUsername());
            user.setEmail(updateDTO.getEmail());
            user.setFullname(updateDTO.getFullname());
            user.setPhone(updateDTO.getPhone());
            user.setAddress(updateDTO.getAddress());
            return Optional.of(userRepository.save(user));
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> updateAvatar(Integer id, AvatarUpdateDTO avatarUpdateDTO) {
        Optional<User> existingUser = getUserById(id);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setAvatar(avatarUpdateDTO.getAvatar());
            return Optional.of(userRepository.save(user));
        }

        return Optional.empty();
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
