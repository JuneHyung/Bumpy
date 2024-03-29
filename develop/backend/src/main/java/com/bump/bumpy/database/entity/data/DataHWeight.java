package com.bump.bumpy.database.entity.data;

import com.bump.bumpy.database.entity.composite.DataHWeightId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DATA_H_WEIGHT", schema = "bumpy", indexes = {
        @Index(name = "PRIMARY", columnList = "stdDate, userId, seq", unique = true),
        @Index(name = "WEIGHT_USER_ID_idx", columnList = "userId")
})
@IdClass(DataHWeightId.class)
public class DataHWeight implements Serializable {
    private static final long serialVersionUID = 74026321731262616L;

    @Id
    @NotNull
    @Column(name = "stdDate", nullable = false)
    private Date stdDate;

    @Id
    @Size(max = 20)
    @NotNull
    @Column(name = "userId", nullable = false, length = 20)
    @JsonIgnore
    private String userId;

    @Id
    @NotNull
    @Column(name = "seq", nullable = false)
    private Integer seq;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "weightStart", precision = 10)
    private BigDecimal weightStart;

    @Column(name = "weightEnd", precision = 10)
    private BigDecimal weightEnd;

    @Column(name = "repsStart")
    private Integer repsStart;

    @Column(name = "repsEnd")
    private Integer repsEnd;

    @Column(name = "pollWeight", precision = 10)
    private BigDecimal pollWeight;

    @Column(name = "setReps")
    private Integer setReps;

    @NotNull
    @Column(name = "measure", nullable = false)
    private Integer measure;

    @Size(max = 500)
    @Column(name = "memo", length = 500)
    private String memo;

    @Size(max = 1000)
    @Column(name = "picture", length = 1000)
    private String picture;

    // set and get picture method for CRUD API split by ',' and join by ','
    public List<String> getPicture() {
        // null check
        if (this.picture == null || this.picture.isEmpty()) {
            return new ArrayList<>();
        }
        return List.of(this.picture.split(","));
    }

    public void setPicture(List<String> picture) {
        // null check
        if (picture == null || picture.isEmpty()) {
            this.picture = null;
            return;
        }
        this.picture = String.join(",", picture);
    }
}