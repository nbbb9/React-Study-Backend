package com.studyreact.sidepj.post;

import com.studyreact.sidepj.post.dto.PostRequest;
import com.studyreact.sidepj.post.dto.PostResponse;
import com.studyreact.sidepj.post.file.PostImageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    PostImageHandler postImageHandler = new PostImageHandler();

    /**
     * 게시글 가져오기
     *
     * @return List<PostResponse>
     * */
    public List<PostResponse> getPosts() {
        return postRepository.findAll().stream()
                .map(PostResponse::toResponse)
                .toList();
    }

    /**
     * 게시글 작성
     *
     * @param
     * @return void
     * */
//    @Transactional
//    public void createPost(PostRequest request, MultipartFile image) {
//        String imageUrl = null;
//
//        if(image != null && !image.isEmpty()){
//          try{
//              String uploadDir = "/home/lyw/Database/reactstudy/media/";
//              Path uploadPath = Paths.get(uploadDir);
//              if (!Files.exists(uploadPath)) {
//                  Files.createDirectories(uploadPath);
//              }
//              String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
//              Path filePath = uploadPath.resolve(fileName);
//              Files.write(filePath, image.getBytes());
//              imageUrl = filePath.toString();
//          }catch(IOException e){
//              throw new RuntimeException("이미지 저장 실패", e);
//          }
//        }
//        Posts post = Posts.builder()
//                            .title(request.title())
//                            .content(request.content()).imageUrl(imageUrl).build();
//
//        postRepository.save(post);
//    }
    @Transactional
    public Posts createPost(PostRequest request)throws IOException {
        //이미지 경로 저장
        String imagePath = postImageHandler.save(request.image());
        Posts entity = request.toEntity(imagePath);
        return postRepository.save(entity);
    }

}