package com.barbel.communityserver.domain.post.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentSaveDto {
    @NotBlank
    public String boardId;

    @NotBlank
    public String userId;

    @NotBlank
    public String content;

    public long good;
}
