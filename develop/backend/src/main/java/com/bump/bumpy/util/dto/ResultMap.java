package com.bump.bumpy.util.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.util.LinkedHashMap;

@AllArgsConstructor
public class ResultMap extends LinkedHashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 1L;
    public Object putData(Object value) { return super.put("data", value); }
    public Object putMsg(String value) { return super.put("message", value); }

    @Builder
    public ResultMap(String key, Object value) {
        if(key == null) {
            super.put("data", value);
        } else {
            super.put(key, value);
        }
    }

    @Builder
    public ResultMap(Object value) {
        super.put("data", value);
    }
}
