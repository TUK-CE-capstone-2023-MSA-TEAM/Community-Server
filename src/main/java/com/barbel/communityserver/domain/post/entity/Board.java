package com.barbel.communityserver.domain.post.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "board")
@Getter
@ToString
public class Board {
    @Id
    private String id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String location;

    @NotBlank
    private String userId;

    private long views;

    // 댓글들의 id만 가지고 있도록 설정
    private List<String> comments = new ArrayList<>();

    @Builder
    public Board(String title,String content,String location,String userId,long views)
    {
        this.title = title;
        this.content = content;
        this.location = location;
        this.userId = userId;
        this.views = views;
    }

    public void addComment(String commentId)
    {
        this.comments.add(commentId);
    }
}
