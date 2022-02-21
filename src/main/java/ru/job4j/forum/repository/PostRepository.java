package ru.job4j.forum.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Post;

import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long> {
    @EntityGraph(attributePaths = {"user", "comments"})
    Optional<Post> findById(long id);
}