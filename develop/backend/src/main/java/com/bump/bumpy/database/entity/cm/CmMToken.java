package com.bump.bumpy.database.entity.cm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

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
    private Date expired;

}