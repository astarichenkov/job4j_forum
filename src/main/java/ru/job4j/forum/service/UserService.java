package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserMemRepository;

import java.util.List;

@Service
public class UserService {
    private final UserMemRepository users;

    public UserService(UserMemRepository users) {
        this.users = users;
    }

    public List<User> findAll() {
        return users.findAll();
    }

    public User save(User user) {
        return users.save(user);
    }
}
