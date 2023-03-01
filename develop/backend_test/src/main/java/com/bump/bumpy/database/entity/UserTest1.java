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
import lombok.ToString;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_test1", schema = "bumpy", indexes = {
        @Index(name = "email_UNIQUE", columnList = "email", unique = true),
        @Index(name = "id_UNIQUE", columnList = "id", unique = true)
})
public class UserTest1 implements Serializable {
    private static final long serialVersionUID = 8505866935022097646L;
    @Id
    @Column(name = "`key`", nullable = false)
    private Integer key;

    @Size(max = 45)
    @NotNull
    @Column(name = "id", nullable = false, length = 45)
    private String id1;

    @Size(max = 45)
    @NotNull
    @Column(name = "pw", nullable = false, length = 45)
    private String pw;

    @Size(max = 45)
    @NotNull
    @Column(name = "email", nullable = false, length = 45)
    private String email;

}