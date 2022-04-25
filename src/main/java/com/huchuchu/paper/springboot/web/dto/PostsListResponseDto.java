package com.huchuchu.paper.springboot.web.dto;

import com.huchuchu.paper.springboot.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Getter
public class PostsListResponseDto {

    private Long id;        //posts Id
    private String title;
    private String author;
    private String modifiedDate;


    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author =  entity.getAuthor();
        this.modifiedDate = yyMmDd(entity.getModifiedDate());
    }

    public String yyMmDd(LocalDateTime modifiedDateTime){
        LocalDate toDay = LocalDate.now();
        if(toDay.isEqual(modifiedDateTime.toLocalDate())){
            return modifiedDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return modifiedDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
    }


}
