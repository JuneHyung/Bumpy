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
public class DataHInbodyId implements Serializable {
    private static final long serialVersionUID = 1183322964940259232L;
    private Date stdDate;
    private String userId;
}