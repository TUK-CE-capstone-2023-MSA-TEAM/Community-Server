package com.barbel.communityserver.domain.match.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;


@Getter
@ToString
@Document(collection = "match")
public class Match {
    @Id
    private String id;
    @NotBlank
    private String mentorEmail;
    @NotBlank
    private String menteeEmail;

    private Date date;


    @Builder
    public Match(String id,String mentorEmail,String menteeEmail,Date date)
    {
        this.id = id;
        this.mentorEmail = mentorEmail;
        this.menteeEmail = menteeEmail;
        this.date = date;
    }

}
