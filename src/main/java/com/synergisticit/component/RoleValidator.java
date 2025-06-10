package com.synergisticit.component;

import com.synergisticit.domain.Role;
import com.synergisticit.repository.RoleRepository;
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
public class RoleValidator implements Validator {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Role.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Role role = (Role) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                "roleName",
                "roleName.empty",
                "Role name is required"
        );

        if (errors.hasFieldErrors("roleName")) {
            System.out.println("Empty role name detected - skipping duplicate check");
            return;
        }

        Role existingRole = roleRepository.findByRoleName(role.getRoleName().trim());
        if (existingRole != null) {
            boolean isCreatingNew = role.getRoleId() == null;
            boolean isSameEntity = existingRole.getRoleId().equals(role.getRoleId());

            if (isCreatingNew || !isSameEntity) {
                errors.rejectValue(
                        "roleName",
                        "roleName.duplicate",
                        "Role name already exists"
                );
            }
        }
    }
}
