package com.example.jobx_api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobRequestDto {

    private Long memberId;

    private String jobCd;

    private boolean isBookmark;

}
