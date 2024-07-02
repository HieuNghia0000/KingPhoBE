package org.application.kingphobe.controller;

import jakarta.validation.Valid;
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
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    public ResponseEntity<User> addUser(@Valid @RequestBody RegisterDTO user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @Valid @RequestBody UpdateDTO updateDTO) {
        Optional<User> optionalUser = userService.updateUser(id, updateDTO);

        return optionalUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }
}
