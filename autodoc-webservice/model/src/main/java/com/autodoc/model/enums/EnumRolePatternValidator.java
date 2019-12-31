package com.autodoc.model.enums;

import org.apache.log4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class EnumRolePatternValidator implements ConstraintValidator<EnumRolePattern, Enum<?>> {
    private static final Logger LOGGER = Logger.getLogger(EnumRolePatternValidator.class);
    private Pattern pattern;

    @Override
    public void initialize(EnumRolePattern annotation) {
        try {
            pattern = Pattern.compile(annotation.regexp());
            LOGGER.info("pattern valid");
        } catch (PatternSyntaxException e) {
            LOGGER.error("pattern is invalid");
            throw new IllegalArgumentException("Given regex is invalid", e);
        }
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if (value == null) {
            LOGGER.info("is null");
            return true;
        }

        Matcher m = pattern.matcher(value.name());
        LOGGER.info("m: " + m);
        return m.matches();
    }


}
