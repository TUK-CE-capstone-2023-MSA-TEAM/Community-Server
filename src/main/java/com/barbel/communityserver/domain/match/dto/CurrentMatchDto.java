package com.barbel.communityserver.domain.match.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentMatchDto {
    String matchId;
    public String mentorEmail;
    public String menteeEmail;
    public Date matchDate;
    public boolean MentorOk;
    public boolean MenteeOk;
}
