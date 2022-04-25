package com.huchuchu.paper.springboot.web;


import com.huchuchu.paper.springboot.config.auth.LoginUser;
import com.huchuchu.paper.springboot.config.auth.dto.SessionUser;
import com.huchuchu.paper.springboot.service.CommentService;
import com.huchuchu.paper.springboot.web.dto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/v1/comment/{id}/comment")
    public Long saveComment(@RequestBody CommentRequestDto requestDto, @LoginUser SessionUser user){

        requestDto.setUserId(user.getId());
        requestDto.setUserName(user.getName());


        System.out.println("=====");
        System.out.println("잘 들어갔나 확인");
        System.out.println("requestDto.getPostId() "+requestDto.getPostId());
        System.out.println("user.getName() "+user.getName());

        return commentService.saveComment(requestDto);
    }



}
