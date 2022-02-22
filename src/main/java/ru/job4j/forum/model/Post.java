package ru.job4j.forum.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Calendar created;

    @OneToMany(cascade = CascadeType.DETACH, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Post(String name) {
        this.name = name;
        this.created = new GregorianCalendar();
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}