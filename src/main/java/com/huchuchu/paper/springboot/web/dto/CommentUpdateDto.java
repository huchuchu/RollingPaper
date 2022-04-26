package com.huchuchu.paper.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateDto {

    private String comment;
    private Long id;
    private Long postId;

    @Builder
    public CommentUpdateDto(String comment, Long id, Long postId){
        this.comment = comment;
        this.id = id;
        this.postId = postId;
    }


}
