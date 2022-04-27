package com.huchuchu.paper.springboot.web;

import com.huchuchu.paper.springboot.config.auth.LoginUser;
import com.huchuchu.paper.springboot.config.auth.dto.SessionUser;
import com.huchuchu.paper.springboot.domain.posts.Comment;
import com.huchuchu.paper.springboot.domain.posts.CommentRepository;
import com.huchuchu.paper.springboot.domain.posts.Posts;
import com.huchuchu.paper.springboot.service.PostsService;
import com.huchuchu.paper.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final CommentRepository commentRepository;

    private final HttpSession httpSession;

    @GetMapping("/posts/userInfo")
    public String userInfo(@LoginUser SessionUser user, Model model){


        model.addAttribute("name", user.getName() );
        model.addAttribute("user", user);
        return "UserInfo";
    }


    @GetMapping("/")                                                /*페이징*/
    public String index(Model model, @LoginUser SessionUser user, @PageableDefault(sort = "id",size = 5,direction = Sort.Direction.DESC)Pageable pageable){

        // 게시판은 페이징 sessionUser는 어노테이션으로 구현
/*      model.addAttribute("posts", postsService.findAllDesc());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");*/

        Page<Posts> list = postsService.pageList(pageable);

        model.addAttribute("posts", list);

        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev",list.hasPrevious() );



        if(user != null){
            model.addAttribute("name",user.getName());
        }

        return "index";
    }

    /*posts 등록페이지로 가기*/
    @GetMapping("/posts/save")
    public String postSave(Model model, @LoginUser SessionUser user){

        model.addAttribute("name", user.getName());
        return "posts-save";
    }


    /*수정페이지로 가기*/
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user){

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        model.addAttribute("name", user.getName() );

        return "posts-update";

    };



    /*게시글 보기*/
    @GetMapping("/posts/select/{id}")
    public String postSelect(@PathVariable Long id, Model model, @LoginUser SessionUser user){

        /*조회수 증가*/
        postsService.updateView(id);
        
        /*게시글 가져오기*/
        PostsResponseDto dto = postsService.findById(id);

        /*댓글*/
        List<Comment> list = commentRepository.commentList(dto.getId());
        if(list.size()>0){
            model.addAttribute("comments", list);
        }

        /*본인인지 체크 :: 글의 삭제 수정버튼*/
        if (user.getId().equals(dto.getUserId())) {
            model.addAttribute("myPosts", true);
        }


        model.addAttribute("loginId", user.getId());
        model.addAttribute("name", user.getName());
        model.addAttribute("post", dto);
        return "posts-select";
    }





}
