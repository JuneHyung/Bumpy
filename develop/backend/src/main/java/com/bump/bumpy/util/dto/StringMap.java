package com.bump.bumpy.util.dto;

import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashMap;

@AllArgsConstructor
public class StringMap extends LinkedHashMap<String, String> implements Serializable {
    private static final long serialVersionUID = 2L;
    public String putData(String value) { return super.put("data", value); }
    public String putMsg(String value) { return super.put("message", value); }
}
