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
public class BoardReplyDto {
    @NotBlank
    public String title;

    @NotBlank
    public String content;

    public String location;

    public String userId;

    public long views;

    public List<String> comments = new ArrayList<>();
}
