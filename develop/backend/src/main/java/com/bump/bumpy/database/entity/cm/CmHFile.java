package com.bump.bumpy.database.entity.cm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Builder
@Table(name = "CM_H_FILE", schema = "bumpy")
@NoArgsConstructor
@AllArgsConstructor
public class CmHFile {
    @Id
    @Size(max = 40)
    @Column(name = "fileId", nullable = false, length = 40, columnDefinition = "char(40)")
    private String fileId;

    @NotNull
    @Column(name = "userId", nullable = false)
    private String userId;

    @Size(max = 1000)
    @NotNull
    @Column(name = "originFileName", nullable = false, length = 1000)
    private String originFileName;

}