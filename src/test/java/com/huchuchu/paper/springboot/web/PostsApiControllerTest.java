package com.huchuchu.paper.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huchuchu.paper.springboot.config.auth.dto.SessionUser;
import com.huchuchu.paper.springboot.domain.posts.Posts;
import com.huchuchu.paper.springboot.domain.posts.PostsRepository;
import com.huchuchu.paper.springboot.web.dto.PostsSaveRequestDto;
import com.huchuchu.paper.springboot.web.dto.PostsUpdateRequestDto;
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
public class PostsApiControllerTest {

    @Autowired
    private WebApplicationContext context;


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;


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
        postsRepository.deleteAll();
        session.clearAttributes();
    }


    @Test
    @WithMockUser(roles = "USER")
    public void Posts_등록된다() throws Exception{
        //given

        String title = "테스트 게시글";
        String content = "테스트 본문";
        String ectDate = "2022-04-27";


        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .ectDate(ectDate)
                .build();



        String url = "http://localhost:"+port+"/api/v1/posts";

        //when
/*
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);*/


        mvc.perform(post(url)
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);



    }



    @Test
    @WithMockUser(roles = "USER")
    public void Posts_수정된다() throws Exception{
        // given

        Posts savePosts = postsRepository.save(Posts.builder()
        .title("title")
        .author("aothor")
        .content("content")
        .userId(Long.valueOf(1))
        .ectDate("2022-05-27")
        .build());

        Long updateId = savePosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:"+port+"/api/v1/posts/"+updateId;


        // when
/*      HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);*/

        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        // then


        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);



    }

    @Test
    @WithMockUser(roles = "USER")
    public void Posts_삭제된다() throws Exception {

        //given
        Posts savePosts = postsRepository.save(Posts.builder()
                .title("title")
                .author("aothor")
                .content("content")
                .userId(Long.valueOf(1))
                .ectDate("2022-05-27")
                .build());

        Long deleteId = savePosts.getId();

        String url = "http://localhost:"+port+"/api/v1/posts/"+deleteId;
        mvc.perform(delete(url)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }


}
