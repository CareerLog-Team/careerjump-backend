package org.careerjump.server.auth;


import lombok.RequiredArgsConstructor;
import org.careerjump.server.jwt.JwtProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtProvider provider;


    @GetMapping
    public String createTestToken() {
        String testUserId = "k_3508669419";
        return provider.create(testUserId);
    }
}
