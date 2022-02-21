package ru.job4j.forum.model;


import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private Calendar created;

    public Comment(String text) {
        this.text = text;
        this.created = new GregorianCalendar();
    }
}