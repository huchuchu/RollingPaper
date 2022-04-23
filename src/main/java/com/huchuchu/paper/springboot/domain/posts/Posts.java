package com.huchuchu.paper.springboot.domain.posts;

import com.huchuchu.paper.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

/*    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;*/

    private String author;

    private String dDay;

    private Long userId;



    @Builder
    public Posts(String title, String author, String dDay, Long userId){
        this.title = title;

        this.author = author;
        this.dDay = dDay;
        this.userId = userId;
    }

    public void update(String title, String content){
        this.title = title;

    }


}
