package com.mission.globalknowledge.controller;

import com.mission.globalknowledge.dto.PostDto;
import com.mission.globalknowledge.entity.Post;
import com.mission.globalknowledge.service.PostService;
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

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final TxId txId;

    @GetMapping("/post")
    public ResponseEntity<?> findAll() {
        log.info("###_{} PostController findAll",txId);
        List<PostDto.Response.Post> postList = postService.findAll();
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> findPost(@PathVariable(value = "id")Long id) {
        log.info("###_{} PostController findPost",txId);
        PostDto.Response.Post post = postService.findById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<?> save(PostDto.Request.Save postDto) {
        postService.save(postDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

