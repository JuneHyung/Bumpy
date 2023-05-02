package com.bump.bumpy.util.customannotation;

import com.bump.bumpy.constants.BigDecimalFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.bump.bumpy.util.funtion.FieldValueUtil.bigDecimalReformat;
import static com.bump.bumpy.util.funtion.FieldValueUtil.bigDecimalRescale;

public class BigDecimalSerializer extends StdSerializer<BigDecimal> implements ContextualSerializer {

    private int scale;
    private RoundingMode mode;
    private BigDecimalFormat format;

    // 2. annotation의 value를 적용하며 생성
    public BigDecimalSerializer(int scale, RoundingMode mode, BigDecimalFormat format) {
        super(BigDecimal.class);
        this.scale = scale;
        this.mode = mode;
        this.format = format;
    }

    public BigDecimalSerializer() {
        super(BigDecimal.class);
    }

    // 1. annotation 여부를 확인하여 value를 확인
    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        BigDecimalScale ann = null;
        if (property != null) {
            ann = property.getAnnotation(BigDecimalScale.class);
        }
        if (ann != null) {
            int scale = ann.scale();
            RoundingMode mode = ann.mode();
            BigDecimalFormat format = ann.format();
            return new BigDecimalSerializer(scale, mode, format);
        } else {
            return this;
        }
    }

    // 3. 직렬화
    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if(format == null) {
            gen.writeString(String.valueOf(value));
        } else if (format.equals(BigDecimalFormat.PERCENT)) {
            int tempScale = scale + 2;
            String expression = "0." + "0".repeat(Math.max(0, scale)) + "%";
            String result = String.valueOf(bigDecimalReformat(bigDecimalRescale(value, tempScale, mode), expression));
            gen.writeString(result);
        } else if (format.equals(BigDecimalFormat.MILLION_DOLLAR_COMMA)) {
            String expression = "#,###";
            if (scale > 0) expression += "." + "0".repeat(scale);
            String result = String.valueOf(bigDecimalReformat(bigDecimalRescale(value, scale, mode), expression));
            gen.writeString(result);
        } else {
            String result = String.valueOf(bigDecimalRescale(value, scale, mode));
            gen.writeString(result);
        }
    }
}
