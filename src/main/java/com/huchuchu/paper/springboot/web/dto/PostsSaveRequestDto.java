package com.huchuchu.paper.springboot.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huchuchu.paper.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String author;

    @JsonProperty("dDay")
    private String dDay;

    private Long userId;

    @Builder
    public PostsSaveRequestDto(String title,  String author, String dDay, Long userId){
        this.title = title;
        this.author = author;
        this.dDay = dDay;
        this.userId = userId;
    }



    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .author(author)
                .dDay(dDay)
                .userId(userId)
                .build();
    }


}
