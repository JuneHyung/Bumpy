package com.bump.bumpy.database.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "CM_C_MEALNM", schema = "bumpy", indexes = {
        @Index(name = "PRIMARY", columnList = "mealNmId", unique = true)
})
public class CmCMealnm implements Serializable {
    private static final long serialVersionUID = 677669512857824651L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "mealNmId", nullable = false)
    private Long mealNmId;

    @Size(max = 1000)
    @NotNull
    @Column(name = "name", nullable = false, length = 1000)
    private String name;

}