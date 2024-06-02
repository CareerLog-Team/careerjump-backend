package org.careerjump.server.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.careerjump.server.user.domain.CustomOAuth2User;
import org.careerjump.server.user.domain.User;
import org.careerjump.server.user.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(request);
        String oauthClientName = request.getClientRegistration().getClientName();

        try {
            System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        User user = null;
        String userId = null;
        String email = null;
        String nickname = null;
        String profileImageUrl = null;

        if (oauthClientName.equals("kakao")) {
            Map<String, Object> userAttributes = oAuth2User.getAttributes();
            Map<String, Object> properties = (Map<String, Object>) userAttributes.get("properties");
            Map<String, Object> kakao_account = (Map<String, Object>) userAttributes.get("kakao_account");

            userId = "k_" + userAttributes.get("id");
            email = (String) kakao_account.get("email");
            nickname = (String) properties.get("nickname");
            profileImageUrl = (String) properties.get("profile_image");

            user = new User(userId, email, "kakao", nickname, profileImageUrl);

            userRepository.save(user);
        }

        return new CustomOAuth2User(userId);
    }

}
