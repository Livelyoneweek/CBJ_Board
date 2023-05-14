package com.mission.globalknowledge;

import com.mission.globalknowledge.repository.PostRepository;
import com.mission.globalknowledge.repository.PostWordRepository;
import com.mission.globalknowledge.service.PostWordService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GlobalKnowledgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlobalKnowledgeApplication.class, args);
    }

    //init data
    @Bean
    public InitData initData(PostRepository postRepository, PostWordRepository postWordRepository) {
        return new InitData(postRepository,postWordRepository);
    }
}
