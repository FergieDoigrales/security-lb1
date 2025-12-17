package com.doigrales.fergie.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequest {
    @NotBlank private String title;
    @NotBlank private String content;
}
