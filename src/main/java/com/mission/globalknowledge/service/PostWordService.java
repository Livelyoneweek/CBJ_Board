package com.mission.globalknowledge.service;

import com.mission.globalknowledge.dto.PostDto;
import com.mission.globalknowledge.entity.Post;
import com.mission.globalknowledge.entity.PostWord;
import com.mission.globalknowledge.repository.PostWordRepository;
import com.mission.globalknowledge.util.TxId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class PostWordService {

    private final PostWordRepository postWordRepository;
    private final TxId txId;

    public void save(PostDto.Request.Save postDto, Post post) {
        log.info("###_{} PostWordService save",txId);
        List<String> words = parsingWord(postDto.getContent());

        List<PostWord> postWords = words.stream().map(word -> new PostWord(word, post)).toList();
        postWordRepository.saveAll(postWords);
    }

    /**
     * content -> List<String> words
     */
    private List<String> parsingWord(String content) {
        List<String> words = new ArrayList<>();
        if (content != null && !content.isEmpty()) {
            String[] strings = content.replaceAll("\\s+", " ").trim().split(" ");
            Set<String> uniqueWords = new HashSet<>(Arrays.asList(strings));
            words.addAll(uniqueWords);
        }
        return words;
    }
}
