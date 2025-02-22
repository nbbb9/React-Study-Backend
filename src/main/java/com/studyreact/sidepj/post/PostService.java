package com.studyreact.sidepj.post;

import com.studyreact.sidepj.base.service.BaseService;
import com.studyreact.sidepj.post.dto.PostRequest;
import com.studyreact.sidepj.post.dto.PostResponse;
import com.studyreact.sidepj.post.file.PostImageHandler;
import com.studyreact.sidepj.user.UserRepository;
import configs.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
public class PostService extends BaseService {

    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;

    public PostService(JwtUtil jwtUtil, PostRepository postRepository, UserRepository userRepository){
        super(jwtUtil, userRepository);
        this.postRepository = postRepository;
        this.jwtUtil = jwtUtil;
    }

    PostImageHandler postImageHandler = new PostImageHandler();

    /**
     * 게시글 가져오기
     * @return List<PostResponse>
     * */
    public List<PostResponse> getPosts() {
        return postRepository.findAll().stream()
                .map(PostResponse::toResponse)
                .toList();
    }

    /**
     * 게시글 작성
     * @param request
     * @return String
     * */
    @Transactional
    public Posts createPost(PostRequest request, String token, List<MultipartFile> imageFiles) {
        //이미지 경로 저장
//        String imageUrl = postImageHandler.save(request.image());
        String imageUrl = "가나다라";
        Posts post = new Posts();
        post.save(request, imageUrl, getUserByToken(token));
        return postRepository.save(post);
    }

    /**
     * 게시글 수정
     * @param request
     * @return String
     */
//    @Transactional
//    public String updatePost(PostRequest request){
//
//    }

}