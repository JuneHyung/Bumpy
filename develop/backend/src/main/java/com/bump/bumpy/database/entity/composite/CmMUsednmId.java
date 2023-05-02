package com.bump.bumpy.database.entity.composite;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class CmMUsednmId implements Serializable {
    private static final long serialVersionUID = 2445075855101070468L;
    private String userId;
    private Integer kind;
    private String name;
}