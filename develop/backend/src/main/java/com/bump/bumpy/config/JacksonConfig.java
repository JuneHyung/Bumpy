package com.bump.bumpy.config;


import com.bump.bumpy.util.customannotation.BigDecimalSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

@Configuration
public class JacksonConfig {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String TIME_FORMAT = "HH:mm:ss";

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        SimpleDateFormat defaultDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.KOREA);
        return builder -> {
            // Date 설정
            builder.timeZone(TimeZone.getTimeZone("Asia/Seoul"));
            builder.locale(Locale.KOREA);
            builder.simpleDateFormat(DATE_TIME_FORMAT);
            builder.dateFormat(defaultDateFormat);
            
            // 직렬화 설정
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT, Locale.KOREA)));
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.KOREA)));
            builder.serializers(new LocalTimeSerializer(DateTimeFormatter.ofPattern(TIME_FORMAT, Locale.KOREA)));
            builder.serializersByType(Map.of(BigDecimal.class, new BigDecimalSerializer())); // BigDeciaml Custom Serializer 사용 - Custom Annotation 적용 및 String으로 변환 처리 포함
            builder.serializationInclusion(JsonInclude.Include.NON_NULL); // null이면 해당 필드가 사라짐

            builder.visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            builder.failOnUnknownProperties(false);
        };
    }
}