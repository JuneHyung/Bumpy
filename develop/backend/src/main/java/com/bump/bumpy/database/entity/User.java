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
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "USER_M", schema = "bumpy", indexes = {
        @Index(name = "email_UNIQUE", columnList = "email", unique = true),
        @Index(name = "id_UNIQUE", columnList = "user_id", unique = true)
})
public class User implements Serializable {
    private static final long serialVersionUID = -1702784117870346566L;

    @Id
    @Size(max = 20, min = 5)
    @NotNull
    @Column(name = "user_id", nullable = false, length = 20, unique = true)
    private String userId;

    @Size(max = 20, min = 8)
    @NotNull
    @Column(name = "pw", nullable = false, length = 20, columnDefinition = "varchar(20) binary")
    private String pw;

    @Size(max = 45)
    @NotNull
    @Column(name = "email", nullable = false, length = 45, unique = true)
    private String email;

    @Size(max = 1)
    @NotNull
    @Column(name = "use_yn", nullable = false, length = 1)
    private String useYn;

    public boolean isUsed() {
        return Objects.equals(this.useYn, "Y");
    }
}