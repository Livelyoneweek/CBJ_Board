package com.mission.globalknowledge.service;

import com.mission.globalknowledge.repository.PostWordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class PostWordService {

    private final PostWordRepository postWordRepository;

}
