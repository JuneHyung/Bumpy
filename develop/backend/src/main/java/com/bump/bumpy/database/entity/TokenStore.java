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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "TOKEN_STORE", schema = "bumpy")
public class TokenStore implements Serializable {
    private static final long serialVersionUID = -1702784237870346566L;

    @Id
    @Size(max = 20, min = 5)
    @NotNull
    @Column(name = "user_id", nullable = false, length = 20, unique = true)
    private String userId;

    @Size(max = 200)
    @NotNull
    @Column(name = "token", nullable = false, length = 200)
    private String token;

    @NotNull
    @Column(name = "exp_date", nullable = false)
    private Date expDate;

    public boolean isExpired() { return !Date.from(Instant.now()).after(this.expDate); }
}