package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.CommentService;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

@Controller
public class PostControl {
    private final PostService posts;
    private final CommentService comments;
    private final UserService users;

    public PostControl(PostService posts, CommentService comments, UserService users) {
        this.posts = posts;
        this.comments = comments;
        this.users = users;
    }

    @GetMapping({"/create"})
    public String create(Model model) {
        model.addAttribute("user", getCurrentUser());
        return "post/create";
    }

    @GetMapping({"/update"})
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("post", posts.findById(id));
        model.addAttribute("user", getCurrentUser());
        return "post/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        User user = getCurrentUser();
        post.setUser(user);
        posts.save(post);
        return "redirect:/";
    }

    @GetMapping({"/view"})
    public String view(@RequestParam("id") Long id, Model model) {
        Post post = posts.findById(id);
        model.addAttribute("post", post);
        model.addAttribute("comments", post.getComments());
        model.addAttribute("user", getCurrentUser());
        return "post/view";
    }

    @PostMapping("/comment/save")
    public String save(@RequestParam("post_id") Long postId, @ModelAttribute Comment comment,
                       RedirectAttributes attributes) {
        Post post = posts.findById(postId);
        comment = comments.save(comment);
        comment.setUser(getCurrentUser());
        post.addComment(comment);
        posts.save(post);
        attributes.addAttribute("id", postId);
        return "redirect:/view";
    }

    private User getCurrentUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return users.findByUsername(name).get();
    }
}