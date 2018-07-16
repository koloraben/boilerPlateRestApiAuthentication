package com.novway.buildit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Chantier extends BaseEntity {

    @Column(name = "code", columnDefinition = "char(80)")
    //@Size(max = 2)
    private String code;
}
