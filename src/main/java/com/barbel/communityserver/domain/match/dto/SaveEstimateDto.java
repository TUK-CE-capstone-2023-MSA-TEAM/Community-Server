package com.barbel.communityserver.domain.match.dto;

import lombok.Data;

@Data
public class SaveEstimateDto {
    int cost;

    String userId;

    String content;

    String type;
}
