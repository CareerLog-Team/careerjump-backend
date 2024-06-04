package org.careerjump.server.utils;

import org.careerjump.server.user.domain.User;
import org.careerjump.server.user.domain.UserRole;

import java.util.UUID;

public class UserUtils {

    public static User getUser(String email, String type, String password, String nickname, String profileImageUrl) {
        return User.builder()
                .userId(String.valueOf(UUID.randomUUID()))
                .email(email)
                .type(type)
                .password(password)
                .role(UserRole.ROLE_USER)
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .build();
    }

    public static User getAdminUser(String email, String type, String password, String nickname, String profileImageUrl) {
        return User.builder()
                .userId(String.valueOf(UUID.randomUUID()))
                .email(email)
                .type(type)
                .password(password)
                .role(UserRole.ROLE_ADMIN)
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .build();
    }

}
