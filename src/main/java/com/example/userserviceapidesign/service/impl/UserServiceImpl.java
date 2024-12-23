package com.example.userserviceapidesign.service.impl;

import com.example.userserviceapidesign.exceptions.UserNotFoundException;
import com.example.userserviceapidesign.mapstruct.UserMapper;
import com.example.userserviceapidesign.models.dto.UserDTO;
import com.example.userserviceapidesign.models.entity.User;
import com.example.userserviceapidesign.repo.UserRepository;
import com.example.userserviceapidesign.service.inter.UserServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceInter {


    private final UserRepository userRepository;


    private final UserMapper userMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if ((userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) &&
                (userDTO.getNumber() == null || userDTO.getNumber().isEmpty())) {
            throw new RuntimeException("Either email or number must be provided");
        }

        if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty() && userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Duplicate email: " + userDTO.getEmail());
        }
        if (userDTO.getNumber() != null && !userDTO.getNumber().isEmpty() && userRepository.existsByNumber(userDTO.getNumber())) {
            throw new RuntimeException("Duplicate number: " + userDTO.getNumber());
        }
        if (userRepository.findUserByUserId(userDTO.getUserId()).isPresent()) {
            throw new RuntimeException("Duplicate userId: " + userDTO.getUserId());
        }
        User user = userMapper.toEntity(userDTO);

      User savedUser=  userRepository.save(user);
      return userMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO getUserByUserId(String userId) {
        User user = userRepository.findUserByUserId(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO updateUser(String userId, UserDTO userDTO) {
        User existingUser = userRepository.findUserByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Qeydiyyat zamanı istifadə olunan email və ya nömrənin tələb olunması
        if (existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            // Əgər istifadəçi yalnız email ilə qeydiyyatdan keçib və update zamanı email təqdim edilməlidir
            if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty() &&
                    !existingUser.getEmail().equals(userDTO.getEmail())) {
                throw new RuntimeException("Email is required as the user was registered with an email.");
            }
        }

        if (existingUser.getNumber() != null && !existingUser.getNumber().isEmpty()) {
            // Əgər istifadəçi yalnız telefon nömrəsi ilə qeydiyyatdan keçib və update zamanı telefon nömrəsi təqdim edilməlidir
            if (userDTO.getNumber() != null && !userDTO.getNumber().isEmpty() &&
                    !existingUser.getNumber().equals(userDTO.getNumber())) {
                throw new RuntimeException("Phone number is required as the user was registered with a phone number.");
            }
        }

        // Yalnız yenilənən sahələr dəyişdirilir, digərləri olduğu kimi qalır
        if (userDTO.getName() != null) {
            existingUser.setName(userDTO.getName());
        }

        if (userDTO.getSurname() != null) {
            existingUser.setSurname(userDTO.getSurname());
        }

        if (userDTO.getDateOfBirth() != null) {
            existingUser.setDateOfBirth(userDTO.getDateOfBirth());
        }

        if (userDTO.getGender() != null) {
            existingUser.setGender(userDTO.getGender());
        }

        if (userDTO.getCountry() != null) {
            existingUser.setCountry(userDTO.getCountry());
        }

        if (userDTO.getCity() != null) {
            existingUser.setCity(userDTO.getCity());
        }

        if (userDTO.getProfileImageUrl() != null) {
            existingUser.setProfileImageUrl(userDTO.getProfileImageUrl());
        }

        if (userDTO.getCoverImageUrl() != null) {
            existingUser.setCoverImageUrl(userDTO.getCoverImageUrl());
        }

        // Email və nömrə üçün yalnız yeni dəyərlər varsa yenilənir
        if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()) {
            existingUser.setEmail(userDTO.getEmail());
        }

        if (userDTO.getNumber() != null && !userDTO.getNumber().isEmpty()) {
            existingUser.setNumber(userDTO.getNumber());
        }

        return userMapper.toDTO(userRepository.save(existingUser));
    }

    @Override
    public String deleteUser(String userId) {
        return userRepository.findUserByUserId(userId)
                .map(user -> {
                    userRepository.delete(user);
                    return "User with userId " + userId + " successfully deleted";
                })
                .orElseThrow(() -> new UserNotFoundException("User with userId " + userId + " not found"));
    }
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));
        return userMapper.toDTO(user);
    }
    public String deleteUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .map(user -> {
                    userRepository.delete(user);
                    return "User with email " + email + " successfully deleted";
                })
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));
    }
    @Override
    public UserDTO updateProfileImage(String userId, String profileImageUrl) {
        UserDTO userDTO = getUserByUserId(userId);
        userDTO.setProfileImageUrl(profileImageUrl);
        return updateUser(userId, userDTO);
    }

    @Override
    public UserDTO updateCoverImage(String userId, String coverImageUrl) {
        UserDTO userDTO = getUserByUserId(userId);
        userDTO.setCoverImageUrl(coverImageUrl);
        return updateUser(userId, userDTO);
    }

    @Override
    public UserDTO deleteProfileImage(String userId) {
        UserDTO userDTO = getUserByUserId(userId);
        userDTO.setProfileImageUrl(null);
        return updateUser(userId, userDTO);
    }

    @Override
    public UserDTO deleteCoverImage(String userId) {
        UserDTO userDTO = getUserByUserId(userId);
        userDTO.setCoverImageUrl(null);
        return updateUser(userId, userDTO);
    }
}
