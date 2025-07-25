package com.example.jobx_api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

    private Long memberId;

    private String memberEmail;

    private String memberPassword;

    private String memberMbti;

    private String memberBirth;

    private String memberName;

    private String memberGender;

    private LocalDateTime memberCreateDate;


}
