package com.bump.bumpy.util.customannotation;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
	
	String message() default "Invalid PasswordDto";
	Class<?>[] groups() default {};
	Class<?>[] payload() default {};
}
