package ru.job4j.forum.model;

import lombok.*;

import java.util.Calendar;
import java.util.Objects;

@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@Getter
@Setter
@ToString
public class Post {

    private long id;

    @NonNull
    private String name;
    private String description;
    private Calendar created;

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