package com.mission.globalknowledge.repository;

import com.mission.globalknowledge.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post>findByIdIn(List<Long> postId);
}
