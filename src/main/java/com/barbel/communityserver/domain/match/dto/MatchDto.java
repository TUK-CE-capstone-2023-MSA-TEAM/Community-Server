package com.barbel.communityserver.domain.match.dto;

import com.barbel.communityserver.domain.match.handler.DateHandler;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.Date;

@Data
public class MatchDto {

    public String mentorEmail;
    public String menteeEmail;
    public String matchDate;

    public MatchDto(String mentorEmail,String menteeEmail,String date)
    {
        this.mentorEmail = mentorEmail;
        this.menteeEmail = menteeEmail;
        this.matchDate = date;
    }
}
