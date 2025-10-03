package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false, unique=true, length=100)
    private String name;   // username

    @Column(nullable=false, unique=true, length=150)
    private String email;

    @Column(nullable=false, length=200)
    private String password;

    @Column(nullable=false, length=200)
    private String roles;  // ROLE_USER, ROLE_ADMIN
}
