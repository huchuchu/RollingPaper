package com.huchuchu.paper.springboot.web.dto;


import com.huchuchu.paper.springboot.domain.posts.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@NoArgsConstructor
public class CommentRequestDto {

    private Long id;
    private String comment;
    private String createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm"));
    private Long userId;
    private Long postId;
    private String userName;

    @Builder
    public CommentRequestDto(String comment, String createDate, String modifiedDate, Long userId, Long  postId, String userName){
        this.comment = comment;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.userId = userId;
        this.postId = postId;
        this.userName = userName;
    }

    public Comment toEntity(){
        return Comment.builder()
                .comment(comment)
                .createdDate(createDate)
                .modifiedDate(modifiedDate)
                .userId(userId)
                .postId(postId)
                .userName(userName)
                .build();
    }




}
