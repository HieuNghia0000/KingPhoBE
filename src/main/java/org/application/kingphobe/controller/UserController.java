package org.application.kingphobe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.application.kingphobe.dto.AvatarUpdateDTO;
import org.application.kingphobe.dto.RegisterDTO;
import org.application.kingphobe.dto.UpdateDTO;
import org.application.kingphobe.model.User;
import org.application.kingphobe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/users", produces = "application/json")
@Tag(name = "Users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    @Operation(summary = "Get all users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    @Operation(summary = "Register new user")
    public ResponseEntity<User> addUser(@Valid @RequestBody RegisterDTO user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping("update/{id}")
    @Operation(summary = "Update user")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @Valid @RequestBody UpdateDTO updateDTO) {
        Optional<User> optionalUser = userService.updateUser(id, updateDTO);

        return optionalUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("update-avatar/{id}")
    @Operation(summary = "Update user avatar")
    public ResponseEntity<User> updateAvatar(@PathVariable("id") int id, @Valid @RequestBody AvatarUpdateDTO avatarUpdateDTO) {
        Optional<User> optionalUser = userService.updateAvatar(id, avatarUpdateDTO);

        return optionalUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "Delete user")
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }
}
