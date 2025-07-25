package com.example.jobx_api.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponseDto {

    private String accessToken;

    private String refreshToken;

    private String message;
}
