package mk.ukim.finki.emt2025.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt2025.model.domain.User;
import mk.ukim.finki.emt2025.model.enumerations.Role;

import mk.ukim.finki.emt2025.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(

            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {


        userRepository.save(new User(
                "sm",
                passwordEncoder.encode("sm"),
                "Sime",
                "Minoski",
                Role.ROLE_ADMIN
        ));

        userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "user",
                "user",
                Role.ROLE_USER
        ));
        userRepository.save(new User(
                "li",
                passwordEncoder.encode("li"),
                "linrarian1",
                "linrarian1",
                Role.ROLE_LIBRARIAN
        ));
    }
}