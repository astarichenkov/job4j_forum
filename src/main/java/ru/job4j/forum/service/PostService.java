package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class PostService {
    private int id;
    private final List<Post> posts = new ArrayList<>();

    public PostService() {
    }

    public List<Post> findAll() {
        return posts;
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public Post save(Post post) {
        post.setId(id++);
        post.setCreated(new GregorianCalendar());
        posts.add(post);
        return post;
    }

    public Post update(Post post) {
        post.setCreated(new GregorianCalendar());
        posts.set(post.getId(), post);
        return post;
    }
}