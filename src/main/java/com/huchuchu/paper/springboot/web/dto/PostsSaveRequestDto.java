package com.huchuchu.paper.springboot.web.dto;

import com.huchuchu.paper.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String author;
    private String content;
    private Long userId;    //게시글 작성자 Id
    private String ectDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm"));


    @Builder
    public PostsSaveRequestDto(String title, String content,String author, Long userId, String ectDate){
        this.title = title;
        this.author = author;
        this.content = content;
        this.userId = userId;
        this.ectDate = ectDate;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .author(author)
                .content(content)
                .userId(userId)
                .ectDate(ectDate)
                .build();
    }
    
    /*저장할 때 userId와 author 추가*/
    public void addUserIdAndAuthor(Long userId, String author){
        this.userId = userId;
        this.author = author;
    }


}
