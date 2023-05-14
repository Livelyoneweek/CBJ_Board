package com.mission.globalknowledge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(indexes = @Index(name = "idx_postId", columnList = "postId"))
@NoArgsConstructor
@AllArgsConstructor
public class PostWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String word;

    @Column(nullable = false)
    private Long postId;

    public PostWord(String word, Long postId) {
        this.word = word;
        this.postId = postId;
    }
}
