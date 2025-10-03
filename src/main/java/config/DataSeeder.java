package config;

import entity.UserInfo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import repository.UserInfoRepository;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedUsers(UserInfoRepository repo, PasswordEncoder encoder) {
        return args -> {
            // ADMIN
            repo.findByName("admin").orElseGet(() -> repo.save(
                    UserInfo.builder()
                            .name("admin")
                            .email("admin@example.com")
                            .password(encoder.encode("123"))   // BCrypt
                            .roles("ROLE_ADMIN")
                            .build()
            ));
            // USER
            repo.findByName("user").orElseGet(() -> repo.save(
                    UserInfo.builder()
                            .name("user")
                            .email("user@example.com")
                            .password(encoder.encode("123"))   // BCrypt
                            .roles("ROLE_USER")
                            .build()
            ));
        };
    }
}
