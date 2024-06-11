package org.careerjump.server.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.careerjump.server.handler.OAuth2SuccessHandler;
import org.careerjump.server.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.List;

@Configurable
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig implements WebMvcConfigurer {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final DefaultOAuth2UserService oAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // csrf, cors
        http
                .csrf(CsrfConfigurer::disable)
                .cors(CorsConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)

                // 세션 관리 상태 없음 관리는 Jwt로 진행
                .sessionManagement(
                        sessionManagement -> sessionManagement.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )
                .authorizeHttpRequests(
                        (request) -> request
                                .requestMatchers("/", "/api/v1/auth/**", "/oauth2/**", "/manage/**", "/api/v1/log/**").permitAll()
                                .requestMatchers("/api/v1/user/**").hasRole("USER")
                                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .oauth2Login(
                        (oauth2) -> oauth2
                                .authorizationEndpoint((endpoint) -> endpoint.baseUri("/api/v1/auth/oauth2"))
                                .redirectionEndpoint((endpoint) -> endpoint.baseUri("/oauth2/callback/*"))
                                .userInfoEndpoint((endpoint) -> endpoint.userService(oAuth2UserService))
                                .successHandler(oAuth2SuccessHandler)
                )
                .exceptionHandling(
                        exceptionHandling -> exceptionHandling
                                .authenticationEntryPoint(new FailedAuthenticationEntryPoint())
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

//    @Bean
//    protected CorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//
//        corsConfiguration.setAllowedOrigins(List.of("http://localhost:8080", "http://localhost:3000")); // 모든 출처에 대해서 허용
//        corsConfiguration.setAllowedMethods(List.of("GET", "PUT", "POST", "DELETE", "OPTIONS")); // 모든 메서드에 대해서 허용
//        corsConfiguration.setAllowedHeaders(List.of("*")); // 모든 헤더에 대해서 허용
//        corsConfiguration.setAllowCredentials(true); //
//        corsConfiguration.setMaxAge(3600L);
//
//        source.registerCorsConfiguration("/**", corsConfiguration); // 모든 패던과 모든 기준에 대해서 허용
//        return source;
//    }


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    /* V1, V2 다른 기준을 적용하고 싶을 때 사용
    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfigurationV1 = new CorsConfiguration();
        corsConfigurationV1.addAllowedOrigin("*"); // 모든 출처에 대해서 허용
        corsConfigurationV1.addAllowedMethod("*"); // 모든 메서드에 대해서 허용
        corsConfigurationV1.addAllowedHeader("*"); // 모든 헤더에 대해서 허용

        CorsConfiguration corsConfigurationV2 = new CorsConfiguration();
        corsConfigurationV2.addAllowedOrigin("*"); // 모든 출처에 대해서 허용
        corsConfigurationV2.addAllowedMethod("*"); // 모든 메서드에 대해서 허용
        corsConfigurationV2.addAllowedHeader("*"); // 모든 헤더에 대해서 허용


        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/v1/**", corsConfigurationV1); // 모든 패던과 모든 기준에 대해서 허용
        source.registerCorsConfiguration("/api/v2/**", corsConfigurationV2); // 모든 패던과 모든 기준에 대해서 허용

        return source;
    }
     */

}


class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        // {"code": "NP", "message": "No Permission"}
        response.getWriter().write("{\"code\": \"NP\", \"message\": \"No Permission\"}");
    }
}