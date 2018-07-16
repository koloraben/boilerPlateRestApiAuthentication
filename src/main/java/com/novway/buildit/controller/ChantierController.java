package com.novway.buildit.controller;

import com.novway.buildit.entity.Chantier;
import com.novway.buildit.exception.customException.ElementNotFoundException;
import com.novway.buildit.repository.ChantierRepository;
import com.novway.buildit.service.interfaces.ChantierServiceInterface;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.ws.spi.http.HttpExchange;
import org.hibernate.mapping.Set;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 */
@RestController
@RequestMapping(value = "/chantier")
public class ChantierController {
  @Autowired
  private ChantierServiceInterface chantierServiceInterface;
  @GetMapping(path = "/")
  public List<Chantier> creatAndreturn() throws ElementNotFoundException {
    /*KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
    KeycloakPrincipal principal=(KeycloakPrincipal)token.getPrincipal();
    KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
    AccessToken accessToken = session.getToken();
    String username = accessToken.getPreferredUsername();
    String emailID = accessToken.getEmail();
    String lastname = accessToken.getFamilyName();
    String firstname = accessToken.getGivenName();
    String realmName = accessToken.getIssuer();
    String picture = accessToken.getPicture();
    System.out.println("#####################################");
    System.out.println(emailID);
    AccessToken.Access realmAccess = accessToken.getRealmAccess();
    //Set roles = (Set) realmAccess.getRoles();
    System.out.println(accessToken);*/
    if(true){
      //throw new ElementNotFoundException("message custom");
    }

    return chantierServiceInterface.getAllChantier();

  }

  @GetMapping(path = "/logout")
  public String logout(HttpServletRequest request) throws ServletException {
    request.logout();
    return "logout";
  }

  /**
   *
   * @param id the id of a chantier
   * @return one single chantier that have the requested id
   * @throws ElementNotFoundException
   */
  @PreAuthorize("hasRole('admindddddd')")
  @GetMapping(path = "/{id}")
  public Chantier findChantier(@PathVariable(value = "id") Long id) throws ElementNotFoundException {
    Chantier chantier = chantierServiceInterface.getChantier((Long) id);
    if(chantier==null){
      throw new ElementNotFoundException("chantier with id: " + id + " not found");
    }
    return chantier;
  }
}
