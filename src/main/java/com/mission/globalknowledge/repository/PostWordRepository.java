package com.mission.globalknowledge.repository;

import com.mission.globalknowledge.entity.PostWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostWordRepository extends JpaRepository<PostWord,Long> {

    List<Long> findAllPostId();
    List<String> findAllWordByPostId(Long postId);
}
