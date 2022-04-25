package com.huchuchu.paper.springboot.service;

import com.huchuchu.paper.springboot.domain.posts.File;
import com.huchuchu.paper.springboot.domain.posts.FileRepository;
import com.huchuchu.paper.springboot.web.dto.FileRequestDto;
import com.huchuchu.paper.springboot.web.dto.FileResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FileService {

    private final FileRepository fileRepository;

    @Transactional
    public File saveFile(FileRequestDto requestDto){
        return fileRepository.save(requestDto.toEntity());
    }

    @Transactional
    public FileResponseDto findById(Long id){
        File file = fileRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 파일이 없습니다"+id));
        return new FileResponseDto(file);
    }






}
