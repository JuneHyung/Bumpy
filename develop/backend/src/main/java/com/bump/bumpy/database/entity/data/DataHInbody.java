package com.bump.bumpy.database.entity.data;

import com.bump.bumpy.database.entity.composite.DataHInbodyId;
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
@Table(name = "DATA_H_INBODY", schema = "bumpy", indexes = {
        @Index(name = "INBODY_USER_ID_idx", columnList = "userId"),
        @Index(name = "PRIMARY", columnList = "stdDate, userId", unique = true)
})
@IdClass(DataHInbodyId.class)
public class DataHInbody implements Serializable {
    private static final long serialVersionUID = 3364908228672838984L;

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

    @Column(name = "height", precision = 10)
    private BigDecimal height;

    @Column(name = "weight", precision = 10)
    private BigDecimal weight;

    @Column(name = "muscle", precision = 10)
    private BigDecimal muscle;

    @Column(name = "fat", precision = 10)
    private BigDecimal fat;

    @Column(name = "score", precision = 10)
    private BigDecimal score;

    @Column(name = "bmi", precision = 10)
    private BigDecimal bmi;

    @Column(name = "fatRate", precision = 10)
    private BigDecimal fatRate;

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