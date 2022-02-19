package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

@Controller
public class PostControl {
    private final PostService posts;

    public PostControl(PostService posts) {
        this.posts = posts;
    }

    @GetMapping({"/create"})
    public String create(Model model) {
        return "post/create";
    }

    @GetMapping({"/update"})
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", posts.findById(id));
        return "post/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        posts.save(post);
        return "redirect:/";
    }
}