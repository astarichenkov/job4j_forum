package ru.job4j.forum.repository;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PostMemRepository {
    private final AtomicLong id;
    private final Map<Long, Post> posts = new HashMap<>();

    public PostMemRepository() {
        this.id = new AtomicLong(0);
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    public Post findById(long id) {
        return posts.get(id);
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(id.incrementAndGet());
        }
        post.setCreated(new GregorianCalendar());
        posts.put(post.getId(), post);
        return post;
    }
}