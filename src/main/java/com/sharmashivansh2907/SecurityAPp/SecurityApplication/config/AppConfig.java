package com.sharmashivansh2907.SecurityAPp.SecurityApplication.config;


import com.sharmashivansh2907.SecurityAPp.SecurityApplication.aud.AuditorAwareImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditAwareImpl") // attaching the auditor with the JPA
public class AppConfig {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @Bean
    public AuditorAware<String> getAuditAwareImpl(){
        return new AuditorAwareImpl();
    }
}
