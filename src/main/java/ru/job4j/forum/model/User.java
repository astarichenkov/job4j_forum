package ru.job4j.forum.model;

import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@Getter
@Setter
@ToString
public class User {

    private Integer id;

    @NonNull
    private String username;

    @NonNull
    private String password;



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}