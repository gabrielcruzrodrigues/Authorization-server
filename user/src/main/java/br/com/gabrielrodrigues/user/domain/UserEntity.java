package br.com.gabrielrodrigues.user.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Type type;
    private OffsetDateTime createdAt;

    public UserEntity(String name, String email, String password, Type type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.createdAt = OffsetDateTime.now();
    }
}
