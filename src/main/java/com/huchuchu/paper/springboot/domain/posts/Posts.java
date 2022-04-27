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
    private Long id;        // 게시글 Id

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String content;

    @Column (nullable = false)
    private String author;

    @Column (nullable = false)
    private Long userId;        //게시글 작성자 Id

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

    private String ectDate;

    @Builder
    public Posts(String title, String content, String author, Long userId, int view, String ectDate ){
        this.title = title;
        this.content = content;
        this.author = author;
        this.userId = userId;
        this.view = view;
        this.ectDate = ectDate;
    }

    public void update(String title, String content, String ectDate){
        this.title = title;
        this.content = content;
        this.ectDate = ectDate;

    }



}
