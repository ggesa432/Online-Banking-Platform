package com.synergisticit.component;

import com.synergisticit.domain.Customer;
import jakarta.validation.Validation;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;

/**
 * @author GesangZeren
 * @project OnlineBank - Assessment
 * @date 1/29/2025
 */
@Component
public class CustomerValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerName", "customer.name.required","Customer name is required");

        if (customer.getCustomerGender()==null){
            errors.rejectValue("customerGender", "customer.gender.required","Customer gender is required");
        }

        if (customer.getCustomerDOB()==null){
            errors.rejectValue("customerDOB", "customer.dob.required","Customer Date of Birth is required");
        }else if ( customer.getCustomerDOB().isAfter(LocalDate.now())){
            errors.rejectValue("customerDOB", "customer.dob.future","Date of Birth can not be in the future");
        }

        if (customer.getCustomerAddress()==null){
            errors.rejectValue("customerAddress", "customer.address.required","Address is required");
        }else {
            errors.pushNestedPath("customerAddress");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addressLine1", "address.line1.required","Address Line 1 is required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "address.city.required","City is required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "address.state.required","State is required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zipCode", "address.zipcode.required","ZipCode is required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "address.country.required","Country is required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "address.phoneNumber.required","Phone Number is required");

            String zipCode = customer.getCustomerAddress().getZipCode();
            if (zipCode!=null && !zipCode.matches("^\\d{5}(-\\d{4})?$")){
                errors.rejectValue("zipCode", "customer.address.zipcode.invalid","Invalid Zip Code format (use ##### or #####-####)");
            }
            String phoneNumber = customer.getCustomerAddress().getPhoneNumber();
            if (phoneNumber!=null && !phoneNumber.matches("^\\d{10}$")){
                errors.rejectValue("phoneNumber","customer.address.phoneNumber.invalid","Invalid Phone number format (should be 10 numbers)");
            }

            errors.popNestedPath();
        }

        String ssn=customer.getCustomerSSN();
        if (ssn==null||ssn.trim().isEmpty()){
            errors.rejectValue("customerSSN", "customer.ssn.required","Customer SSN is required");
        }else if (!ssn.matches("^\\d{3}-\\d{2}-\\d{4}$")){
            errors.rejectValue("customerSSN", "customer.ssn.invalid","Invalid SSN format(use XXX-XX-XXXX)");
        }

        if (customer.getUser()==null){
            errors.rejectValue("user", "customer.user.required","Assigned User is required");
        }

    }
}
