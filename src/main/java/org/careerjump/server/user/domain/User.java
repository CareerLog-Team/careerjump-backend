package org.careerjump.server.user.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.careerjump.server.careerboard.domain.CareerBoard;
import org.careerjump.server.common.entity.BaseTimeEntity;
import org.careerjump.server.goal.domain.Goal;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "USER")
@Table(name = "USER_TABLE")
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "TYPE")
    private String type;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private UserRole role;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "PROFILE_IMAGE_URL")
    private String profileImageUrl;

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true
    )
    private Goal goal;

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true
    )
    private CareerBoard careerBoard;


    public User(String userId, String email, String type, String nickname, String profileImageUrl) {
        this.userId = userId;
        this.password = "";
        this.email = email;
        this.type = type;
        this.role = UserRole.ROLE_USER;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    public void updateGoal(Goal goal) {
        this.goal = goal;
    }
    public void updateCareerBoard(CareerBoard careerBoard) {
        this.careerBoard = careerBoard;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", role=" + role +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
