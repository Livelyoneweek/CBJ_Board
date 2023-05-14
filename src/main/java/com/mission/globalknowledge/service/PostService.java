package com.mission.globalknowledge.service;

import com.mission.globalknowledge.dto.PostDto;
import com.mission.globalknowledge.entity.Post;
import com.mission.globalknowledge.repository.PostRepository;
import com.mission.globalknowledge.util.TxId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final TxId txId;

    @Transactional(readOnly = true)
    public List<PostDto.Response.Post> findAll() {
        log.info("###_{} PostService findAll",txId);

        List<Post> all = postRepository.findAll();

        return changeDto(all);
    }

    @Transactional(readOnly = true)
    public PostDto.Response.DetailPost findById(Long id) {
        log.info("###_{} PostService findById",txId);

        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();

            return new PostDto.Response.DetailPost(
                    post.getId(),post.getTitle(),post.getContent(),post.getCreateDate(),post.getModifiedDate());
        }

        log.info("###_{} findById is null",txId);
        return new PostDto.Response.DetailPost();
    }

    @Transactional(readOnly = true)
    public List<PostDto.Response.Post> findByIds(List<Long> postIdList) {
        log.info("###_{} PostService findByIds",txId);

        // 3개까지만 출력
        List<Post> postList;
        if (postIdList.size() > 3) {
            List<Long> firstThreePostIds = postIdList.stream().limit(3).toList();
            postList = postRepository.findByIdIn(firstThreePostIds);
        } else {
            postList = postRepository.findByIdIn(postIdList);
        }

        return changeDto(postList);
    }

    public Post save(PostDto.Request.Save save) {
        log.info("###_{} PostService save",txId);
        Post post = new Post(save);
        return postRepository.save(post);
    }

    /**
     * List<Post> -> List<PostDto.Response.PostList>
     */
    private List<PostDto.Response.Post> changeDto(List<Post> postList) {
        if (postList != null) {
            return postList.stream()
                    .map(m -> PostDto.Response.Post.builder()
                            .id(m.getId())
                            .title(m.getTitle())
                            .createDate(m.getCreateDate())
                            .modifiedDate(m.getModifiedDate())
                            .build())
                    .collect(Collectors.toList());
        } else {
            log.info("###_{} post is null",txId);
            return new ArrayList<>();
        }
    }


}
