package com.example.jobx_api.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchRequestDto {

    private String jobCd;

    private String findText;
}
