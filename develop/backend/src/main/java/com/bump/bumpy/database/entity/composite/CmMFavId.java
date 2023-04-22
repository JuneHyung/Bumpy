package com.bump.bumpy.database.entity.composite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CmMFavId implements Serializable {
    private static final long serialVersionUID = -7789168434503085308L;

    private Integer kind;

    private String name;
}