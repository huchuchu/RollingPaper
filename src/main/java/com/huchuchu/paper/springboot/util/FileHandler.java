package com.huchuchu.paper.springboot.util;

import com.huchuchu.paper.springboot.web.dto.FileRequestDto;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class FileHandler {

    public FileRequestDto forSaveFile(MultipartFile file){

        String origFilename = "";
        String filename = "";
        String filePath = "";

        String contentType = file.getContentType();
        String origFileExtention;

        /*확장자가 없으면 이 파일은 잘못된것*/
        if(ObjectUtils.isEmpty(contentType)) {
            return null;
        }

        if(contentType.contains("image/jpeg")){
            origFileExtention = ".jpg";
        }else if (contentType.contains("image/png")){
            origFileExtention = ".png";
        }else if(contentType.contains("image/gif")){
            origFileExtention = ".gif";
        }else{
            return null;
        }


        try{
            origFilename  = file.getOriginalFilename();
            filename = new MD5Generator(origFilename).toString();


            /*실행되는 위치의 절대경로를 얻는다*/
            String savePath = System.getProperty("user.dir")+"\\images";

            /*폴더가 없으면 만들어라*/
            if(!new File(savePath).exists()){
                try {
                    new File(savePath).mkdir();
                }catch (Exception e){
                    e.getStackTrace();
                }
            }

            filePath = savePath +"\\" + filename + origFileExtention;
            /*경로에 파일 생성*/
            file.transferTo(new File(filePath));


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return FileRequestDto.builder()
                .origFilename(origFilename)
                .filename(filename)
                .filePath(filePath)
                .build();

    }

}
