package com.huchuchu.paper.springboot.web;

import com.huchuchu.paper.springboot.config.auth.LoginUser;
import com.huchuchu.paper.springboot.config.auth.dto.SessionUser;
import com.huchuchu.paper.springboot.service.PostsService;
import com.huchuchu.paper.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){


        model.addAttribute("posts", postsService.findAllDesc());

       /* SessionUser user = (SessionUser) httpSession.getAttribute("user");*/
        if(user != null){

            model.addAttribute("name",user.getName());
        }

        return "index";
    }

    /*posts 등록페이지로 가기*/
    @GetMapping("/posts/save")
    public String postSave(Model model){
        
        //Day 날짜 설정값 내일~일년후 사이 날짜로 설정가능
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalDate term = today.plusYears(1);

        model.addAttribute("tomorrow", tomorrow);
        model.addAttribute("term", term);
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

        PostsResponseDto dto = postsService.findById(id);

        System.out.println("user.getId()+ "+ user.getId() + ", dto.getUserId() " +dto.getUserId() );


        if(user.getId().equals(dto.getUserId()) ){
            model.addAttribute("chk",true);
        }

        model.addAttribute("post", dto);
        return "posts-select";
    }



}
