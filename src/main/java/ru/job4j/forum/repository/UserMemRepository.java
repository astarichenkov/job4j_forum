package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserMemRepository {
    private final AtomicLong id;
    private final Map<Long, User> users = new HashMap<>();

    public UserMemRepository() {
        this.id = new AtomicLong(1);
        users.put(1L, User.of("root", "123"));
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User save(User user) {
        if (user.getId() == 0) {
            user.setId(id.incrementAndGet());
        }
        users.put(user.getId(), user);
        return user;
    }
}