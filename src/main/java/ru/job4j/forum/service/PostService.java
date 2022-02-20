package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostMemRepository;

import java.util.List;

@Service
public class PostService {
    private final PostMemRepository posts;

    public PostService(PostMemRepository posts) {
        this.posts = posts;
    }

    public List<Post> findAll() {
        return posts.findAll();
    }

    public Post findById(long id) {
        return posts.findById(id);
    }

    public Post save(Post post) {
        return posts.save(post);
    }
}
