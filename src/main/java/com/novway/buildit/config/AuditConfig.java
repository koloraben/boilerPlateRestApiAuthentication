package com.novway.buildit.config;


import com.novway.buildit.config.security.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditConfig {
  @Bean
  public AuditorAware<String> auditorAware() {
    return new AuditorAwareImpl();
  }

  public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
      String userName = SecurityUtils.getCurrentUserLogin();
      return userName != null ? userName : "system";
    }
  }
}