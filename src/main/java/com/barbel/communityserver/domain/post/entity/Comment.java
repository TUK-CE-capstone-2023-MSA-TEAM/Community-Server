package com.barbel.communityserver.domain.post.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@Document(collection = "comment")
public class Comment {
    @Id
    private String id;

    private String boardId;

    @NotBlank
    private String userId;

    @NotBlank
    private String content;

    private long good;

    private List<Comment> childList = new ArrayList<>();

    @Builder
    public Comment(String boardId,String userId,String content)
    {
        this.boardId = boardId;
        this.content = content;
        this.userId = userId;
        this.good = 0;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public void addReComment(Comment comment)
    {
        this.childList.add(comment);
    }

    public void setGood(long good) {
        this.good = good;
    }
}
