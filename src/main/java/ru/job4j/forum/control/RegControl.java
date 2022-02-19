package ru.job4j.forum.control;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class RegControl {
    @GetMapping({"/reg"})
    public String index(Model model) {
        return "reg";
    }
}
