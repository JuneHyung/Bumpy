package com.bump.bumpy.domain.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse implements Serializable {

    private String username = null;
    private BigDecimal height = null;
    private BigDecimal weight = null;
    private Integer age = null;
    private Integer continuity = null;
    private Date lastActive = null;
    private BigDecimal averageWater = null;
    private inbodyData inbodyData = null;

    @Builder
    public static class inbodyData {
        private BigDecimal weight;
        private BigDecimal muscle;
        private BigDecimal fat;
        private BigDecimal bmi;
        private BigDecimal fatRate;
    }
}