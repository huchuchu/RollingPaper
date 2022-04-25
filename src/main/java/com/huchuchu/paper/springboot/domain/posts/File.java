package com.huchuchu.paper.springboot.domain.posts;


import com.huchuchu.paper.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class File extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String origFilename;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String filePath;

    @Builder
    public File (String origFilename, String filename, String filePath){
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }



}
