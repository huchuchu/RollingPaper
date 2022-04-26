package com.huchuchu.paper.springboot.web.dto;

import com.huchuchu.paper.springboot.domain.posts.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private Long id;
    private String comment;
    private String createdDate;
    private String modifiedDate;
    private Long postId;
    private Long userId;
    private String userName;

    public CommentResponseDto(Comment entity){
        this.id = entity.getId();
        this.comment = entity.getComment();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
        this.postId = entity.getPostId();
        this.userId = entity.getUserId();
        this.userName = entity.getUserName();
    }






}
