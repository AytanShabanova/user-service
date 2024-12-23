package com.example.userserviceapidesign.service.inter;

import com.example.userserviceapidesign.models.dto.UserDTO;

public interface UserServiceInter {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserByUserId(String userId);
    UserDTO updateUser(String userId, UserDTO userDTO);
    String deleteUser(String userId);
     UserDTO getUserByEmail(String email);
     String deleteUserByEmail(String email);
    UserDTO updateProfileImage(String userId, String profileImageUrl);
    UserDTO updateCoverImage(String userId, String coverImageUrl);
    UserDTO deleteProfileImage(String userId);
    UserDTO deleteCoverImage(String userId);
}
