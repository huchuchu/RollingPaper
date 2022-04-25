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


/*
    @Column(name = "FILE_ID")
    private Long fileId;        //사진파일 Id
    
    private String filePath;   // 사진 저장위치
*/



    @Builder
    public Posts(String title, String content, String author, Long userId, int view ){
        this.title = title;
        this.content = content;
        this.author = author;
        this.userId = userId;
        this.view = view;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;

    }


}
