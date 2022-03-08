package ru.job4j.forum.control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.CommentService;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControlTest {

    @MockBean
    private PostService posts;

    @MockBean
    private CommentService comments;

    @MockBean
    private UserService users;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void prepare() {
        User user = User.of("user", "123");
        Mockito.doReturn(Optional.of(user)).when(users).findByUsername(Mockito.anyString());
    }


    @Test
    @WithMockUser
    public void shouldReturnPostCreate() throws Exception {
        this.mockMvc.perform(get("/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/create"));
    }

    @Test
    @WithMockUser
    public void whenSavePostThenRedirection() throws Exception {
        this.mockMvc.perform(post("/save")
                        .param("name", "Продам ладу-гранту. Дорого."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).save(argument.capture());
        assertThat(argument.getValue().getName(), is("Продам ладу-гранту. Дорого."));
    }

    @Test
    @WithMockUser
    public void whenUpdatePostThenRedirection() throws Exception {
        this.mockMvc.perform(post("/save")
                        .param("name", "Продам ладу-гранту. Дорого.")
                        .param("id", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).save(argument.capture());
        assertThat(argument.getValue().getName(), is("Продам ладу-гранту. Дорого."));
    }

    @Test
    @WithMockUser
    public void whenSaveCommentThenRedirection() throws Exception {
        Post post = new Post("Продам ладу-гранту. Дорого.");
        post.setId(1L);
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setText("Почем продаешь?");
        Mockito.doReturn(post).when(posts).findById(Mockito.anyLong());
        Mockito.doReturn(comment).when(comments).save(Mockito.any(Comment.class));
        this.mockMvc.perform(post("/comment/save")
                        .param("name", "Почем продаешь?")
                        .param("id", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).save(argument.capture());
        assertThat(argument.getValue().getComments().get(0).getText(), is("Почем продаешь?"));
    }
}