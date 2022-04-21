package com.huchuchu.paper.springboot.web;

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

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return  postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update (@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

/*
    @GetMapping("/api/v1/posts/save")
    public String postSave(){
        return "posts-save";
    }
*/




}
