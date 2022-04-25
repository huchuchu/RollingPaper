package com.huchuchu.paper.springboot.web;

import com.huchuchu.paper.springboot.config.auth.LoginUser;
import com.huchuchu.paper.springboot.config.auth.dto.SessionUser;
import com.huchuchu.paper.springboot.domain.posts.File;
import com.huchuchu.paper.springboot.service.FileService;
import com.huchuchu.paper.springboot.service.PostsService;
import com.huchuchu.paper.springboot.util.FileHandler;
import com.huchuchu.paper.springboot.web.dto.FileRequestDto;
import com.huchuchu.paper.springboot.web.dto.PostsResponseDto;
import com.huchuchu.paper.springboot.web.dto.PostsSaveRequestDto;
import com.huchuchu.paper.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;
    private final FileService fileService;


    /*Posts 등록_old*/
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto, @LoginUser SessionUser user){

        // 게시글에 userId추가
        requestDto.setUserId(user.getId());
        // 게시글에 author 추가
        requestDto.setAuthor(user.getName());

        return  postsService.save(requestDto);
    }


    /*Posts 수정*/
    @PutMapping("/api/v1/posts/{id}")
    public Long update (@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    /*posts 삭제*/
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }


    /*Post 파일업로드 나중에 사용 */
    @PostMapping(value = "/api/v1/test/posts")
    public void postsSave(MultipartHttpServletRequest request, @LoginUser SessionUser user){

        MultipartFile file =  request.getFile("file");

        File fileEntity = new File();


        // 파일이 null이 아니
        if(!file.isEmpty()){

            FileRequestDto fileRequestDto = new FileHandler().forSaveFile(file);
            fileEntity = fileService.saveFile(fileRequestDto);
        }

        PostsSaveRequestDto postsSaveRequestDto = PostsSaveRequestDto.builder()
                .title(request.getParameter("title"))

                .author(user.getName())
                .userId(user.getId())
/*                .fileId(fileEntity.getId())
                .filePath(fileEntity.getFilePath())*/
                .build();

        postsService.save(postsSaveRequestDto);

    }








}
