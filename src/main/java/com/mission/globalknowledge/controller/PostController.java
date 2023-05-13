package com.mission.globalknowledge.controller;

import com.mission.globalknowledge.dto.PostDto;
import com.mission.globalknowledge.entity.Post;
import com.mission.globalknowledge.service.PostService;
import com.mission.globalknowledge.service.PostWordService;
import com.mission.globalknowledge.util.TxId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

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
        PostDto.Response.Post post = postService.findById(id); //게시글가져옴
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    /**
     * 연간 게시글 조회
     */
    @GetMapping("/post/{id}/relation ")
    public ResponseEntity<?> findRelationPost(@PathVariable(value = "id")Long id) {
        log.info("###_{} PostController findRelationPost",txId);

        PostDto.Response.Post post = postService.findById(id); //게시글가져옴

        // 전제 게시글 40% 이상 String 가져옴
        Set<String> stringList = postWordService.calculateDuplicateWord();

        // 본문의 String[] 에서 60% 계산된 [] 제거함

        // 남은 본문의 String[] 로 postWord 돌면서 유사도 계산

        // 유사도 높은 게시글 반환

        return new ResponseEntity<>(post, HttpStatus.OK);
    }


    // Post 저장 & PostWord
    @PostMapping("/post")
    public ResponseEntity<?> save(PostDto.Request.Save postDto) {
        log.info("###_{} PostController save",txId);

        Post post = postService.save(postDto); // post 저장
        postWordService.save(postDto, post.getId()); // postWord 저장
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

