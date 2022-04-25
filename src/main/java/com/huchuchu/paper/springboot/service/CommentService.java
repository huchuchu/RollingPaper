package com.huchuchu.paper.springboot.service;

import com.huchuchu.paper.springboot.domain.posts.CommentRepository;
import com.huchuchu.paper.springboot.web.dto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public Long saveComment(CommentRequestDto requestDto){
        return commentRepository.save(requestDto.toEntity()).getId();
    }

}
