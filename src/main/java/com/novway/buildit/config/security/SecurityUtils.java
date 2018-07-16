package com.novway.buildit.config.security;


import javax.servlet.http.HttpServletRequest;
import org.hibernate.mapping.Set;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {


  private SecurityUtils() {
  }

  /**
   * Get the login of the current user.
   *
   * @return the login of the current user
   */
  public static String getCurrentUserLogin() {

    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();
    return authentication.getName();
  }

  /**
   * Check if a user is authenticated.
   *
   * @return true if the user is authenticated, false otherwise
   */
  public static boolean isAuthenticated() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();
    if (authentication != null) {
      return authentication.getAuthorities().stream()
          .noneMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(AuthoritiesConstants.ANONYMOUS));
    }
    return false;
  }

  /**
   * If the current user has a specific authority (security role).
   *
   * <p>The name of this method comes from the isUserInRole() method in the Servlet API</p>
   *
   * @param authority the authority to check
   * @return true if the current user has the authority, false otherwise
   */
  public static boolean isCurrentUserInRole(String authority) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();
    if (authentication != null) {
      return authentication.getAuthorities().stream()
          .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority));
    }
    return false;
  }
}