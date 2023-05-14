package com.mission.globalknowledge.repository;

import com.mission.globalknowledge.entity.PostWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostWordRepository extends JpaRepository<PostWord,Long> {

    @Query("select p.postId from PostWord p")
    List<Long> findAllPostId();

    @Query("select p.word from PostWord p where p.postId = :postId")
    List<String> findAllWordByPostId(Long postId);
}
