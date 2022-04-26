package com.huchuchu.paper.springboot.service;

import com.huchuchu.paper.springboot.domain.posts.Comment;
import com.huchuchu.paper.springboot.domain.posts.CommentRepository;
import com.huchuchu.paper.springboot.web.dto.CommentRequestDto;
import com.huchuchu.paper.springboot.web.dto.CommentResponseDto;
import com.huchuchu.paper.springboot.web.dto.CommentUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    /*코멘트 저장*/
    @Transactional
    public Long saveComment(CommentRequestDto requestDto){
        return commentRepository.save(requestDto.toEntity()).getId();
    }

    /*코멘트 리스트*/
    @Transactional
    public List<CommentResponseDto> CommentList(Long postId){

        return commentRepository.commentList(postId).stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    /*코멘트 삭제*/
    @Transactional
    public void deleteComment(Long postId, Long id){

        Comment comment = commentRepository.findComment(postId, id);
        commentRepository.delete(comment);
    }
    
    /*코멘트 수정*/
    @Transactional
    public Long updateComment(CommentUpdateDto dto){

        Comment comment = commentRepository.findById(dto.getId())
        .orElseThrow(()-> new IllegalArgumentException("해당 코멘트가 없습니다"+dto.getId()));

        comment.update(dto.getComment(), dto.getModifiedDate());
        return dto.getId();
    }


}
