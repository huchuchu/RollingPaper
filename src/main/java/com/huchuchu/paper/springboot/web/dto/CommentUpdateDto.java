package com.huchuchu.paper.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class CommentUpdateDto {

    private String comment;
    private Long id;
    private Long postId;
    private String modifiedDate= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm"));

    @Builder
    public CommentUpdateDto(String comment, Long id, Long postId){
        this.comment = comment;
        this.id = id;
        this.postId = postId;
    }


}
