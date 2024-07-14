package org.application.kingphobe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.application.kingphobe.dto.UserFavouriteDTO;
import org.application.kingphobe.service.UserFavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> createUserFavourite(@RequestBody UserFavouriteDTO userFavouriteDTO) {
        try {
            UserFavouriteDTO savedFavourite = userFavouriteService.saveUserFavourite(userFavouriteDTO);
            return new ResponseEntity<>(savedFavourite, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user favourite by user ID")
    public ResponseEntity<?> getUserFavourites(@PathVariable Integer userId) {
        try {
            List<UserFavouriteDTO> favourites = userFavouriteService.getUserFavourites(userId);
            return new ResponseEntity<>(favourites, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/{productId}")
    @Operation(summary = "Get user favourite by user ID and product ID")
    public ResponseEntity<?> getUserFavourite(@PathVariable Integer userId, @PathVariable Integer productId) {
        try {
            UserFavouriteDTO favourite = userFavouriteService.getUserFavourite(userId, productId);
            return new ResponseEntity<>(favourite, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user favourite")
    public ResponseEntity<?> deleteUserFavourite(@PathVariable Integer id) {
        try {
            userFavouriteService.deleteUserFavourite(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

