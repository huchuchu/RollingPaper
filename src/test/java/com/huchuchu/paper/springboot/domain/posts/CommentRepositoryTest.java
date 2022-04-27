package com.huchuchu.paper.springboot.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @AfterEach
    public void clean(){
        commentRepository.deleteAll();
    }

    @Test
    public void 댓글저장_불러오기(){

        //given
        String comment = "테스트 댓글";
        String createDate = "2022-04-27";
        String modifiedDate = "2022-04-22";
        String userName = "huchuchu";
        Long userId = Long.valueOf(1);
        Long podtId = Long.valueOf(1);

        commentRepository.save(Comment.builder()
        .comment(comment)
        .createdDate(createDate)
        .modifiedDate(modifiedDate)
        .userName(userName)
        .userId(userId)
        .postId(podtId)
        .build());

        //when

        List<Comment> list = commentRepository.findAll();

        //then

        Comment comment1 = list.get(0);
        assertThat(comment1.getComment()).isEqualTo(comment);
        assertThat(comment1.getUserName()).isEqualTo(userName);


    }



}
