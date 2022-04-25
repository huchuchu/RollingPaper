package com.huchuchu.paper.springboot.web;

import com.huchuchu.paper.springboot.config.auth.LoginUser;
import com.huchuchu.paper.springboot.config.auth.dto.SessionUser;
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

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    private final HttpSession httpSession;

    @GetMapping("/")                                                /*페이징*/
    public String index(Model model, @LoginUser SessionUser user, @PageableDefault(sort = "id",size = 2,direction = Sort.Direction.DESC)Pageable pageable){

        // 게시판은 페이징 sessionUser는 어노테이션으로 구현
/*      model.addAttribute("posts", postsService.findAllDesc());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");*/

        Page<Posts> list = postsService.pageList(pageable);

        model.addAttribute("posts", list);

        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev",list.hasPrevious() );

        System.out.println("hasNext"+ list.hasNext());
        System.out.println("hasPrev"+ list.hasPrevious());

        if(user != null){
            model.addAttribute("name",user.getName());
        }

        return "index";
    }

    /*posts 등록페이지로 가기*/
    @GetMapping("/posts/save")
    public String postSave(Model model){

        return "posts-save";
    }



    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";

    };



    /*게시글 보기*/
    @GetMapping("/posts/select/{id}")
    public String postSelect(@PathVariable Long id, Model model, @LoginUser SessionUser user){

        postsService.updateView(id); //view++
        PostsResponseDto dto = postsService.findById(id);

        if(user.getId().equals(dto.getUserId()) ){
            model.addAttribute("chk",true);
        }

        model.addAttribute("post", dto);
        return "posts-select";
    }





}
