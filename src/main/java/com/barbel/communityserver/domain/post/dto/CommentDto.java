package com.barbel.communityserver.domain.post.dto;

import com.barbel.communityserver.domain.post.entity.Comment;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    @NotBlank
    public String boardId;

    @NotBlank
    public String userId;

    @NotBlank
    public String content;

    public long good;

    public List<Comment> childList = new ArrayList<>();
}
