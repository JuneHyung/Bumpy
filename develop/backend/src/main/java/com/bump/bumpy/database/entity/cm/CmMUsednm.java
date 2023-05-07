package com.bump.bumpy.database.entity.cm;

import com.bump.bumpy.database.entity.composite.CmMUsednmId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "CM_M_USEDNM", schema = "bumpy", indexes = {
        @Index(name = "PRIMARY", columnList = "userId, kind, name", unique = true)
})
@IdClass(CmMUsednmId.class)
public class CmMUsednm implements Serializable {
    private static final long serialVersionUID = 7456376152041714455L;
    @Id
    @Size(max = 20)
    @NotNull
    @Column(name = "userId", nullable = false, length = 20)
    private String userId;

    @Id
    @NotNull
    @Column(name = "kind", nullable = false)
    private Integer kind;

    @Id
    @Size(max = 1000)
    @NotNull
    @Column(name = "name", nullable = false, length = 1000)
    private String name;

    @Size(max = 1000)
    @Column(name = "memo", length = 1000)
    private String memo;
}