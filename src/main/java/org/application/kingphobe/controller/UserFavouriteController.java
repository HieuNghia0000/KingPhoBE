package org.application.kingphobe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.application.kingphobe.dto.UserFavouriteDTO;
import org.application.kingphobe.service.UserFavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@Tag(name = "Favourites")
public class UserFavouriteController {

    @Autowired
    private UserFavouriteService userFavouriteService;

    @PostMapping
    @Operation(summary = "Create favourite")
    public ResponseEntity<UserFavouriteDTO> createUserFavourite(@RequestBody UserFavouriteDTO userFavouriteDTO) {
        return ResponseEntity.ok(userFavouriteService.saveUserFavourite(userFavouriteDTO));
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user favourite by user ID")
    public ResponseEntity<List<UserFavouriteDTO>> getUserFavourites(@PathVariable Integer userId) {
        return ResponseEntity.ok(userFavouriteService.getUserFavourites(userId));
    }

    @GetMapping("/{userId}/{productId}")
    @Operation(summary = "Get user favourite by user ID and product ID")
    public ResponseEntity<UserFavouriteDTO> getUserFavourite(@PathVariable Integer userId, @PathVariable Integer productId) {
        return ResponseEntity.ok(userFavouriteService.getUserFavourite(userId, productId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user favourite")
    public ResponseEntity<Void> deleteUserFavourite(@PathVariable Integer id) {
        userFavouriteService.deleteUserFavourite(id);
        return ResponseEntity.noContent().build();
    }
}
