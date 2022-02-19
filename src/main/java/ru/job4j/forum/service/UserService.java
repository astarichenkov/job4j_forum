package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private Integer id;
    private final List<User> users = new ArrayList<>();

    public UserService() {
        users.add(User.of("root", "123"));
        id = 2;
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        user.setId(id++);
        users.add(user);
        return user;
    }
}