package com.huchuchu.paper.springboot.web.dto;

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
    private String content;
    private Long userId;    //게시글 작성자 Id




    @Builder
    public PostsSaveRequestDto(String title, String content,String author, Long userId){
        this.title = title;
        this.author = author;
        this.content = content;
        this.userId = userId;
    }



    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .author(author)
                .content(content)
                .userId(userId)
                .build();
    }


}
