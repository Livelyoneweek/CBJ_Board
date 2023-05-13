package com.mission.globalknowledge.repository;

import com.mission.globalknowledge.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
