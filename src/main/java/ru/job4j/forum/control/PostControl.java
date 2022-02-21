package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.CommentService;
import ru.job4j.forum.service.PostService;

@Controller
public class PostControl {
    private final PostService posts;
    private final CommentService comments;

    public PostControl(PostService posts, CommentService comments) {
        this.posts = posts;
        this.comments = comments;
    }

    @GetMapping({"/create"})
    public String create(Model model) {
        return "post/create";
    }

    @GetMapping({"/update"})
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("post", posts.findById(id));
        return "post/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        posts.save(post);
        return "redirect:/";
    }

    @GetMapping({"/view"})
    public String view(@RequestParam("id") Long id, Model model) {
        Post post = posts.findById(id);
        model.addAttribute("post", post);
        model.addAttribute("comments", post.getComments());
        return "post/view";
    }

    @PostMapping("/comment/save")
    public String save(@RequestParam("id") Long id, @ModelAttribute Comment comment) {
        Post post = posts.findById(id);
        comment = comments.save(comment);
        post.addComment(comment);
        posts.save(post);
        return "redirect:/view?id=" + id;
    }
}