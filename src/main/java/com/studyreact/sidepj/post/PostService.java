package com.studyreact.sidepj.post;

import com.studyreact.sidepj.post.dto.PostRequest;
import com.studyreact.sidepj.post.dto.PostResponse;
import com.studyreact.sidepj.post.file.PostImageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
     * @return List<PostResponse>
     * */
    @Transactional
    public void createPost(PostRequest request, List<MultipartFile> image) {
        Posts posts = PostRequest.toPosts(request);
        Posts savePost = postRepository.save(posts);

        if(image != null && !image.isEmpty()) {
            for(MultipartFile multipartFile : image){
                if(multipartFile != null) {
                    /// /////////////////////
                }
            }
        }
    }

}