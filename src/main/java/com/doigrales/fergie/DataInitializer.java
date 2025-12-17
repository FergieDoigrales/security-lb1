package com.doigrales.fergie;

import com.doigrales.fergie.models.User;
import com.doigrales.fergie.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        userRepository.findByUsername("user").orElseGet(() ->
                userRepository.save(User.builder()
                        .username("user")
                        .passwordHash(passwordEncoder.encode("password"))
                        .role("USER")
                        .build())
        );
    }
}

