package com.mission.globalknowledge.entity;

import com.mission.globalknowledge.dto.PostDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false, length = 25)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    public Post(PostDto.Request.Save save) {
        this.title= save.getTitle();
        this.content = save.getContent();
    }

    public Post(String title, String content) {
        this.title= title;
        this.content = content;
    }
}
