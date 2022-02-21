package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.repository.CommentRepository;

import java.util.GregorianCalendar;

@Service
public class CommentService {
    private final CommentRepository comments;

    public CommentService(CommentRepository comments) {
        this.comments = comments;
    }

    public Comment save(Comment comment) {
        comment.setCreated(new GregorianCalendar());
        return comments.save(comment);
    }
}