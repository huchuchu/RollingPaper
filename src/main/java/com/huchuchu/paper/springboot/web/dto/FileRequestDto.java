package com.huchuchu.paper.springboot.web.dto;


import com.huchuchu.paper.springboot.domain.posts.File;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FileRequestDto {

    private String origFilename;
    private String filename;
    private String filePath;

    @Builder
    public FileRequestDto (String origFilename, String filename, String filePath){
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }

    public File toEntity(){
          return File.builder()
                    .origFilename(origFilename)
                    .filename(filename)
                    .filePath(filePath)
                    .build();
    }


}
