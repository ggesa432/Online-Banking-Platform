package com.synergisticit.component;


import com.synergisticit.domain.User;
import com.synergisticit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * @author GesangZeren
 * @project OnlineBank
 * @date 1/27/2025
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        // Validate username
        if (!errors.hasFieldErrors("username")) {
            User existingUser = userRepository.findByUsername(user.getUsername()).orElse(null);
            if (existingUser != null && (user.getUserId() == null || !existingUser.getUserId().equals(user.getUserId()))) {
                errors.rejectValue("username", "username.duplicate", "Username already exists");
            }
        }

        if (!errors.hasFieldErrors("email") && !isValidEmail(user.getEmail())) {
            errors.rejectValue("email", "email.invalid", "Email should be valid");
        }

    }

    private boolean isValidEmail(String email) {
        // Simple email validation regex
        return email != null && email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
}