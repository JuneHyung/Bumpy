package com.bump.bumpy.database.entity.composite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataHCardioId implements Serializable {
    private static final long serialVersionUID = 8411829661158825352L;

    private Date stdDate;
    private String userId;
    private Integer seq;
}