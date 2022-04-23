package com.huchuchu.paper.springboot.web;

import com.huchuchu.paper.springboot.config.auth.LoginUser;
import com.huchuchu.paper.springboot.config.auth.dto.SessionUser;
import com.huchuchu.paper.springboot.service.PostsService;
import com.huchuchu.paper.springboot.web.dto.PostsResponseDto;
import com.huchuchu.paper.springboot.web.dto.PostsSaveRequestDto;
import com.huchuchu.paper.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;


    /*Posts 등록*/
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto, @LoginUser SessionUser user){


        // 게시글에 userId추가
        requestDto.setUserId(user.getId());
        // 게시글에 author 추가
        requestDto.setAuthor(user.getName());

        return  postsService.save(requestDto);
    }

    /*Posts 수정*/
    @PutMapping("/api/v1/posts/{id}")
    public Long update (@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    /*posts 삭제*/
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }








}
