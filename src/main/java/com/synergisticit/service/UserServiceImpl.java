package com.synergisticit.service;

import com.synergisticit.domain.User;
import com.synergisticit.domain.Role;

import com.synergisticit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 12/16/2024
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllByOrderByUsernameAsc();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById((long) Math.toIntExact(id)).orElseThrow(()->new RuntimeException("User not found"));
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean isAdminOrManager = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("ROLE_MANAGER"));

        if (!isAdminOrManager && !existing.getUsername().equals(username)) {
            throw new RuntimeException("Access denied: not your user record");
        }


        if (isAdminOrManager) {
            existing.setUsername(updatedUser.getUsername());
        }


        if (updatedUser.getPassword() != null && !updatedUser.getPassword().trim().isEmpty()) {

            if (!passwordEncoder.matches(updatedUser.getPassword(), existing.getPassword())) {
                existing.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }

        } else {

            existing.setPassword(existing.getPassword());
        }
        existing.setEmail(updatedUser.getEmail());
        existing.setRoles(updatedUser.getRoles());

        return userRepository.save(existing);
    }




    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById((long) Math.toIntExact(id));
    }

    @Override
    public Long getNextUserId() {
        User maxIdUser = userRepository.findTopByOrderByUserIdDesc();
        if (maxIdUser == null) {
            return 1L;
        }
        return maxIdUser.getUserId() + 1;
    }

    @Override
    public List<User> getUsersForLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new RuntimeException("No authenticated user");
        }

        String username = auth.getName();
        boolean isAdminOrManager = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN") ||
                        a.getAuthority().equals("ROLE_MANAGER"));

        if (isAdminOrManager) {
            return userRepository.findAllByOrderByUsernameAsc();
        } else {
            User self = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            return List.of(self);
        }
    }

    @Override
    public boolean usernameExists(String username, Long currentUserId) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() && !user.get().getUserId().equals(currentUserId);
    }





}