package com.doigrales.fergie.DTOs;


import com.doigrales.fergie.controllers.PostController;
import com.doigrales.fergie.models.Post;
import lombok.*;
import org.apache.commons.text.StringEscapeUtils;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private Instant createdAt;
    private String author;

    public static PostResponse from(Post p) {
        return new PostResponse(
                p.getId(),
                StringEscapeUtils.escapeHtml4(p.getTitle()),
                StringEscapeUtils.escapeHtml4(p.getContent()),
                p.getCreatedAt(),
                StringEscapeUtils.escapeHtml4(p.getAuthor().getUsername())
        );
    }
}

