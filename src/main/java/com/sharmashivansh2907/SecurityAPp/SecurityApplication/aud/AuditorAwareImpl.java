package com.sharmashivansh2907.SecurityAPp.SecurityApplication.aud;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() { // will return the name of the person who makes the change in DB
        return Optional.of("Shivansh Sharma");
    }
}
