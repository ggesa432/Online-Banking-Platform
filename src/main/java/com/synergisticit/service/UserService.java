package com.synergisticit.service;

import com.synergisticit.domain.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 12/16/2024
 */
@Service
public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    Optional<User> getUserByUsername(@NotBlank String username);
    User updateUser(Long id,User updatedUser);
    void deleteUser(Long id);
    Long getNextUserId();

    List<User> getUsersForLoggedInUser();

    boolean usernameExists(String username, Long currentUserId);

}
