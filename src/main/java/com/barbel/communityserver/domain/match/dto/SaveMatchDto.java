package com.barbel.communityserver.domain.match.dto;

import lombok.Data;

@Data
public class SaveMatchDto {
    public String mentorEmail;
    public String menteeEmail;
    public String matchDate;

    public boolean isMentor;

    public SaveMatchDto(String mentorEmail, String menteeEmail, String date, boolean isMentor)
    {
        this.mentorEmail = mentorEmail;
        this.menteeEmail = menteeEmail;
        this.matchDate = date;
        this.isMentor = isMentor;
    }


}
