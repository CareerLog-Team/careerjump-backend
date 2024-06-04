package org.careerjump.server.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new NullPointerException("Null Authentication from SecurityUtils");
        }

        if (authentication.isAuthenticated()) {
            return authentication.getName();
        } else {
            throw new RuntimeException("Not Authenticated Authentication!! / from : SeurityUtils.getCurrentUserId");
        }
    }
}