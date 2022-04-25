package com.huchuchu.paper.springboot.web.dto;

import com.huchuchu.paper.springboot.domain.posts.File;
import lombok.Getter;

@Getter
public class FileResponseDto {

    private Long id;
    private String origFilename;
    private String filename;
    private String filepath;


    public FileResponseDto (File entity){
        this.id = entity.getId();
        this.origFilename = entity.getOrigFilename();
        this.filename = entity.getFilename();
        this.filepath = entity.getFilePath();

    }


}
