package com.barbel.communityserver.domain.match.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@ToString
@Document(collection = "Estimate")
public class Estimate {

    @Id
    private String id;

    public int cost;

    public String content;

    public String userEmail;

    public String type;

    @Builder
    public Estimate(String id,int cost,String content,String userEmail,String type)
    {
        this.id =id;
        this.cost = cost;
        this.content = content;
        this.userEmail = userEmail;
        this.type = type;
    }
}
