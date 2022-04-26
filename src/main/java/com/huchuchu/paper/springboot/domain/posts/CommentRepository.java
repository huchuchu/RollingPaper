package com.huchuchu.paper.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.postId= :postId order by c.id desc")
    List<Comment> commentList(Long postId);

    @Query("select c from Comment c where c.postId= :postId and id = :id ")
    Comment findComment(Long postId, Long id);
}
