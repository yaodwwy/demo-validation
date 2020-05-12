package com.example.demo.customize;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author adam
 */
public class CellPhoneValidator implements ConstraintValidator<CellPhone, String> {

    private static final Pattern CELL_PHONE_PATTERN = Pattern.compile(
            "^1\\d{10}$"
    );

    @Override
    public void initialize(CellPhone constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if ( value == null || value.length() == 0 ) {
            return true;
        }
        Matcher m = CELL_PHONE_PATTERN.matcher(value);
        return m.matches();
    }
}