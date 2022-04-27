package com.huchuchu.paper.springboot.service;


import com.huchuchu.paper.springboot.domain.posts.Posts;
import com.huchuchu.paper.springboot.domain.posts.PostsRepository;
import com.huchuchu.paper.springboot.web.dto.PostsListResponseDto;
import com.huchuchu.paper.springboot.web.dto.PostsResponseDto;
import com.huchuchu.paper.springboot.web.dto.PostsSaveRequestDto;
import com.huchuchu.paper.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;



    /*게시글 저장*/
    @Transactional
    public Long save(PostsSaveRequestDto requestDto){

        return postsRepository.save(requestDto.toEntity()).getId();

    }

    /*게시글 수정*/
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){

        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 글이 없습니다. id = "+id));

        posts.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getEctDate());
        return id;
    }


    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 글이 없습니다. id = "+id));

        return new PostsResponseDto(entity);
    }

    /*게시글 삭제*/
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 글이 없습니다.id =" +id));

        postsRepository.delete(posts);
    }


    /*게시글 리스트 페이징*/
    @Transactional(readOnly = true)
    public Page<Posts> pageList(Pageable pageable){
        return postsRepository.findAll(pageable);
    }


    /*게시글 리스트 페이징 없음*/
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    /*조회수 없데이트*/
    @Transactional
    public int updateView(Long id){
        return postsRepository.updateView(id);
    }





}
