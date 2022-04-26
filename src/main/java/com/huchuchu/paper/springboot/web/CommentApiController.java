package com.huchuchu.paper.springboot.web;


import com.huchuchu.paper.springboot.config.auth.LoginUser;
import com.huchuchu.paper.springboot.config.auth.dto.SessionUser;
import com.huchuchu.paper.springboot.service.CommentService;
import com.huchuchu.paper.springboot.web.dto.CommentRequestDto;
import com.huchuchu.paper.springboot.web.dto.CommentUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;
    
    /*comment 작성*/
    @PostMapping("/api/v1/posts/{id}/comment")
    public Long saveComment(@RequestBody CommentRequestDto requestDto, @LoginUser SessionUser user){

/*        requestDto.setUserId(user.getId());
        requestDto.setUserName(user.getName());*/
        
        // userId와 userName을 추가로 저장
        requestDto.addUserIdAndUserName(user.getId(), user.getName());
        return commentService.saveComment(requestDto);
    }

    /*comment 삭제*/
    @DeleteMapping({"/api/v1/posts/{id}/comment/{id}"})
    public Long deleteComment(@RequestBody CommentRequestDto requestDto){

        commentService.deleteComment(requestDto.getPostId(), requestDto.getId());

        return requestDto.getId();
    }

    /*comment 수정*/
    @PutMapping("/api/v1/posts/{id}/comment/{id}")
    public  Long updateComment(@RequestBody CommentUpdateDto updateDto){

        return  commentService.updateComment(updateDto);
    }


}
