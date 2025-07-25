package com.example.jobx_api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDto {

    private String memberEmail;

    private String memberPassword;

    private String memberMbti;

    private String memberBirth;

    private String memberName;

    private String memberGender;

}
