package com.huchuchu.paper.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huchuchu.paper.springboot.config.auth.dto.SessionUser;
import com.huchuchu.paper.springboot.domain.posts.Comment;
import com.huchuchu.paper.springboot.domain.posts.CommentRepository;
import com.huchuchu.paper.springboot.web.dto.CommentRequestDto;
import com.huchuchu.paper.springboot.web.dto.CommentUpdateDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentApiControllerTest {

    @Autowired
    private WebApplicationContext context;


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CommentRepository commentRepository;


    private MockMvc mvc;

    protected MockHttpSession session;

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();

        SessionUser user = SessionUser.builder()
                .id(Long.valueOf(1))
                .name("huchuchu")
                .build();

        session = new MockHttpSession();
        session.setAttribute("user", user);
    }

    @AfterEach
    public void tearDown() throws Exception{
        commentRepository.deleteAll();
        session.clearAttributes();
    }


    @Test
    @WithMockUser(roles = "USER")
    public void Comment_등록된다() throws Exception {

        //given
        String comment = "comment";
        String createDate = "2022-04-12";
        String modifiedDate = "2022-04-12";
        Long postId = Long.valueOf(1);

        CommentRequestDto requestDto = CommentRequestDto.builder()
                .comment(comment)
                .createDate(createDate)
                .modifiedDate(modifiedDate)
                .postId(postId)
                .build();



        String url = "http://localhost:"+port+"/api/v1/posts/"+postId+"/comment";

        //when
        mvc.perform(post(url)
        .session(session)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then

        List<Comment> list = commentRepository.findAll();
        assertThat(list.get(0).getComment()).isEqualTo(comment);

    }

    @Test
    @WithMockUser(roles = "USER")
    public void Comment_수정된다() throws Exception{

        //given

        Comment comment = commentRepository.save(Comment.builder()
        .comment("comment")
        .postId(Long.valueOf(1))
        .createdDate("2022-03-44")
        .modifiedDate("2022-33-43")
        .userName("huchuchu")
        .userId(Long.valueOf(1))
        .build());

        Long updateId = comment.getId();
        Long postId = comment.getPostId();
        String updateComment = "comment2";
        String modifiedDate = "2030-03-03";

        CommentUpdateDto dto = CommentUpdateDto.builder()
                .comment(updateComment)
                .id(updateId)
                .postId(postId)
                .build();

        String url = "http://localhost:"+port+"/api/v1/posts/"+postId+"/comment/"+updateId;

        // when
        mvc.perform(put(url)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());

        //then

        List<Comment> list = commentRepository.findAll();
        assertThat(list.get(0).getComment()).isEqualTo(updateComment);

    }

    @Test
    @WithMockUser(roles = "USER")
    public void Comment_삭제된다() throws Exception{

        //given

        Comment comment = commentRepository.save(Comment.builder()
                .comment("comment")
                .postId(Long.valueOf(1))
                .createdDate("2022-03-44")
                .modifiedDate("2022-33-43")
                .userName("huchuchu")
                .userId(Long.valueOf(1))
                .build());

        Long deleteId = comment.getId();
        Long postId = comment.getPostId();

        CommentRequestDto requestDto = CommentRequestDto.builder()
                .postId(postId)
                .id(deleteId)
                .build();

        String url = "http://localhost:"+port+"/api/v1/posts/"+postId+"/comment/"+deleteId;

        mvc.perform(delete(url)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());


    }




}
