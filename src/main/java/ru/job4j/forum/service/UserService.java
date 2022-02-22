package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository users;

    public UserService(UserRepository users) {
        this.users = users;
    }

    public User save(User user) {
        return users.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return users.findByUsername(username);
    }

}