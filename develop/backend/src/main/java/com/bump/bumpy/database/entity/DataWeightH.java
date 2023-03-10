package com.bump.bumpy.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "DATA_WEIGHT_H", schema = "bumpy")
public class DataWeightH implements Serializable {
    private static final long serialVersionUID = -7137221491689196090L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "user_key", nullable = false)
    private Integer userKey;

    @Size(max = 45)
    @Column(name = "d_name", length = 45)
    private String dName;

    @Size(max = 45)
    @Column(name = "d_weight_start", length = 45)
    private String dWeightStart;

    @Size(max = 45)
    @Column(name = "d_weight_end", length = 45)
    private String dWeightEnd;

    @Size(max = 45)
    @Column(name = "d_count_start", length = 45)
    private String dCountStart;

    @Size(max = 45)
    @Column(name = "d_count_end", length = 45)
    private String dCountEnd;

    @Size(max = 45)
    @Column(name = "d_poll_weight", length = 45)
    private String dPollWeight;

    @Size(max = 45)
    @Column(name = "d_set_count", length = 45)
    private String dSetCount;

    @Size(max = 45)
    @NotNull
    @Column(name = "d_measure", nullable = false, length = 45)
    private String dMeasure;

    @Size(max = 1000)
    @Column(name = "d_memo", length = 1000)
    private String dMemo;

    @Size(max = 200)
    @Column(name = "d_picture", length = 200)
    private String dPicture;
}