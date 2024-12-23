package com.example.userserviceapidesign.controller;

import com.example.userserviceapidesign.models.dto.UserDTO;
import com.example.userserviceapidesign.service.inter.UserServiceInter;
import com.example.userserviceapidesign.validation.CreateUser;
import com.example.userserviceapidesign.validation.UpdateUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {


    private final UserServiceInter userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Validated(CreateUser.class) @RequestBody @Valid UserDTO userDTO) {
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserByUserId(@PathVariable String userId) {
        return new ResponseEntity<>(userService.getUserByUserId(userId), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String userId, @Validated(UpdateUser.class)@RequestBody @Valid UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUser(userId, userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        String responseMessage = userService.deleteUser(userId);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }
    @DeleteMapping("/email/{email}")
    public ResponseEntity<String> deleteUserByEmail(@PathVariable String email) {
        String responseMessage = userService.deleteUserByEmail(email);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
    @PutMapping("/{userId}/profile-image")
    public ResponseEntity<UserDTO> updateProfileImage(@PathVariable String userId, @RequestParam String profileImageUrl) {
        UserDTO updatedUser = userService.updateProfileImage(userId, profileImageUrl);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping("/{userId}/cover-image")
    public ResponseEntity<UserDTO> updateCoverImage(@PathVariable String userId, @RequestParam String coverImageUrl) {
        UserDTO updatedUser = userService.updateCoverImage(userId, coverImageUrl);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/profile-image")
    public ResponseEntity<UserDTO> deleteProfileImage(@PathVariable String userId) {
        UserDTO updatedUser = userService.deleteProfileImage(userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/cover-image")
    public ResponseEntity<UserDTO> deleteCoverImage(@PathVariable String userId) {
        UserDTO updatedUser = userService.deleteCoverImage(userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
