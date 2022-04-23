package com.huchuchu.paper.springboot.web.dto;

import com.huchuchu.paper.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String author;
    private String dDay;
    private Long userId;

/*    private String content;*/

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.dDay = entity.getDDay();
        this.userId = entity.getUserId();
        /*        this.content = entity.getContent();*/
    }


}
