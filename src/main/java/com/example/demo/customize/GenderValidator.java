package com.example.demo.customize;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author adam
 */
public class GenderValidator implements ConstraintValidator<Gender, String> {

    private static final Pattern GENDER_PATTERN = Pattern.compile(
            "MALE|FEMALE"
    );

    @Override
    public void initialize(Gender constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if ( value == null || value.length() == 0 ) {
            return true;
        }
        Matcher m = GENDER_PATTERN.matcher(value);
        return m.matches();
    }
}