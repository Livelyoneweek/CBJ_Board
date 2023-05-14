package com.mission.globalknowledge;

import com.mission.globalknowledge.repository.PostRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GlobalKnowledgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlobalKnowledgeApplication.class, args);
    }

    //init data
    @Bean
    public InitData initData(PostRepository postRepository) {
        return new InitData(postRepository);
    }
}
