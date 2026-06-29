package com.sharmashivansh2907.SecurityAPp.SecurityApplication.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "posts")
@Audited
public class PostEntity extends AuditableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 150)
    private String description;


}
