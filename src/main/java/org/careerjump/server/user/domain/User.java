package org.careerjump.server.user.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "user_table")
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    private String email;
    private String password;
    private String type;
    private String role;
    private String nickname;
    private String profileImageUrl;

    public User(String userId, String email, String type, String nickname, String profileImageUrl) {
        this.userId = userId;
        this.password = "";
        this.email = email;
        this.type = type;
        this.role = "ROLE_USER";
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }
}
