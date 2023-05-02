package com.bump.bumpy.database.entity;

import com.bump.bumpy.database.entity.composite.CmMFavId;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CM_M_FAV", schema = "bumpy", indexes = {
        @Index(name = "PRIMARY", columnList = "kind, name", unique = true)
})
@IdClass(CmMFavId.class)
public class CmMFav implements Serializable {
    private static final long serialVersionUID = 8658315251303553987L;

    @Id
    @NotNull
    @Column(name = "kind", nullable = false)
    private Integer kind;

    @Id
    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;
}