package com.mission.globalknowledge.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

public class PostDto {

    public static class Request {

        @ToString
        @AllArgsConstructor
        @Getter
        public static class Save {
            @NotBlank(message = "title is null")
            private String title;
            @NotBlank(message = "content is null")
            private String content;
        }
    }


    public static class Response {

        @Getter
        @AllArgsConstructor
        @Builder
        @NoArgsConstructor
        public static class Post {
            private Long id;
            private String title;
            private LocalDateTime createDate;
            private LocalDateTime modifiedDate;
        }
    }
}
