package com.novway.buildit.entity;

import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    import javax.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = "id")

public class BaseEntity {
  @Column(name = "id")
  @Id
  @GeneratedValue
  private Long id;
  @CreatedDate
  private long created ;
  @LastModifiedDate
  private long modified ;
  @CreatedBy
  private String created_by;
  @LastModifiedBy
  private String modified_by;

}