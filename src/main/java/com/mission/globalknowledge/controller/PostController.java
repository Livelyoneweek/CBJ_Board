package com.mission.globalknowledge.controller;

import com.mission.globalknowledge.dto.PostDto;
import com.mission.globalknowledge.entity.Post;
import com.mission.globalknowledge.service.PostService;
import com.mission.globalknowledge.service.PostWordService;
import com.mission.globalknowledge.util.TxId;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostWordService postWordService;
    private final TxId txId;

    /**
     * 게시글 리스트 조회
     */
    @GetMapping("/post")
    public ResponseEntity<?> findAll() {
        log.info("###_{} PostController findAll",txId);
        List<PostDto.Response.Post> postList = postService.findAll();
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    /**
     * 게시글 조회
     */
    @GetMapping("/post/{id}")
    public ResponseEntity<?> findPost(@PathVariable(value = "id")Long id) {
        log.info("###_{} PostController findPost",txId);
        PostDto.Response.DetailPost post = postService.findById(id); //게시글가져옴
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    /**
     * 연간 게시글 조회
     */
    @GetMapping("/post/relation/{id}")
    public ResponseEntity<?> findRelationPost(@PathVariable(value = "id")Long id) {
        log.info("###_{} PostController findRelationPost",txId);

        // 게시글가져옴
        PostDto.Response.DetailPost post = postService.findById(id);

        // 연간 게시글 id 가져옴
        List<Long> relationPostIds = postWordService.findRelationPost(post.getId());
        
        // 본문과 같은 id는 삭제
        relationPostIds.removeIf(postId -> postId.equals(id));

        // 연간 게시글 id로 연간 게시글 가져옴
        List<PostDto.Response.Post> postDtoList = postService.findByIds(relationPostIds);

        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }


    // Post 저장 & PostWord
    @PostMapping("/post")
    public ResponseEntity<?> save(@RequestBody @Valid PostDto.Request.Save postDto) {
        log.info("###_{} PostController save",txId);

        Post post = postService.save(postDto); // post 저장
        postWordService.save(postDto, post.getId()); // postWord 저장
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

