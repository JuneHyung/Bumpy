package com.bump.bumpy.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CM_M_TOKEN", schema = "bumpy", indexes = {
        @Index(name = "PRIMARY", columnList = "userId", unique = true)
})
public class CmMToken implements Serializable {
    private static final long serialVersionUID = -3178991595208842706L;
    @Id
    @Size(max = 20)
    @NotNull
    @Column(name = "userId", nullable = false, length = 20)
    private String userId;

    @Size(max = 1000)
    @NotNull
    @Column(name = "refreshToken", nullable = false, length = 1000)
    private String refreshToken;

    @NotNull
    @Column(name = "expired", nullable = false)
    private LocalDate expired;

}