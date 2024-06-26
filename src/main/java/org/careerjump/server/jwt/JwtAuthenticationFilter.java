package org.careerjump.server.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.careerjump.server.user.service.UserService;
import org.careerjump.server.user.domain.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            String token = parseBearerToken(request);
            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }

            String userId = jwtProvider.validateToken(token);
            if (userId == null) {
                filterChain.doFilter(request, response);
                return;
            }

            User user = userService.getUserById(userId);
            String role = user.getRole().name(); // ROLE_USER, ROLE_ADMIN

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));

            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            AbstractAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userId, null, authorities);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            securityContext.setAuthentication(authenticationToken);
            SecurityContextHolder.setContext(securityContext);

            log.debug("""
                    userId : {},
                    Roles : {}""", user.getUserId(), authorities);
            log.debug("securityContext : {}",
                    securityContext.getAuthentication());

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }

    private String parseBearerToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        log.debug("Authorization Token : {}", authorization);

        // 값이 존재하는지 확인
        boolean hasAuthorization = StringUtils.hasText(authorization);
        if (!hasAuthorization) return null;

        // Grant Type == Bearer 인지 확인
        boolean isBearer = authorization.startsWith("Bearer ");
        if (!isBearer) return null;

        String returnAuthorization = authorization.substring(7);
        log.debug("Return Authorization Token : {}", returnAuthorization);

        return returnAuthorization;
    }
}
