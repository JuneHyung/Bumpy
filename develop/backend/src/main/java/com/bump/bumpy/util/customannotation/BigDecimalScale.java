package com.bump.bumpy.util.customannotation;


import com.bump.bumpy.constants.BigDecimalFormat;
import com.fasterxml.jackson.annotation.JacksonAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.RoundingMode;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface BigDecimalScale {
    int scale() default 0;
    RoundingMode mode() default RoundingMode.DOWN;
    BigDecimalFormat format() default BigDecimalFormat.NONE;
}
