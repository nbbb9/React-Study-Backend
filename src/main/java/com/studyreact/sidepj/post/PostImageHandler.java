package com.studyreact.sidepj.post;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
//실제로 서버안에 이미지를 저장하는 부분을 담당
public class PostImageHandler {
    private static final String ROOT_PATH = "/media/post-images/";

    public String save(MultipartFile image) throws IOException {
        String fileName = getOriginName(image);
        String fullPathName = ROOT_PATH + fileName;

        // 디렉터리가 없으면 생성
        File directory = new File(ROOT_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 파일 저장
        image.transferTo(new File(fullPathName));
        return fullPathName;
    }

    private String getOriginName(MultipartFile image) {
        return image.getOriginalFilename(); // 전달받은 이미지 파일의 이름을 반환
    }
}