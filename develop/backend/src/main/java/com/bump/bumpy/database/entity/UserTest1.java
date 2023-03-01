package com.bump.bumpy.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "user_test1", schema = "bumpy", indexes = {
        @Index(name = "email_UNIQUE", columnList = "email", unique = true),
        @Index(name = "id_UNIQUE", columnList = "user_id", unique = true)
})
public class UserTest1 implements Serializable {
    private static final long serialVersionUID = -1702784117870346566L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`key`", nullable = false)
    private Integer key;

    @Size(max = 45)
    @NotNull
    @Column(name = "user_id", nullable = false, length = 45)
    private String userId;

    @Size(max = 45)
    @NotNull
    @Column(name = "pw", nullable = false, length = 45)
    private String pw;

    @Size(max = 45)
    @NotNull
    @Column(name = "email", nullable = false, length = 45)
    private String email;
}