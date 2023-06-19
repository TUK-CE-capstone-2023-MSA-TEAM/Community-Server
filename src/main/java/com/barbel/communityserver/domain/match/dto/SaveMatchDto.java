package com.barbel.communityserver.domain.match.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveMatchDto {
    public String mentorEmail;
    public String menteeEmail;
    public String matchDate;
    public boolean isMentor;
}
