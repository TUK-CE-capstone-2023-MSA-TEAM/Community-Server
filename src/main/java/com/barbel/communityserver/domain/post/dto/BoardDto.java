package com.barbel.communityserver.domain.post.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
    @NotBlank
    public String title;

    @NotBlank
    public String content;

    public String location;

    public String userId;

    public long views;

}
