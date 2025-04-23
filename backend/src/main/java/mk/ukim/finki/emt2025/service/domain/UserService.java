package mk.ukim.finki.emt2025.service.domain;


import mk.ukim.finki.emt2025.model.domain.User;
import mk.ukim.finki.emt2025.model.enumerations.Role;

public interface UserService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    User findByUsername(String username);

}
