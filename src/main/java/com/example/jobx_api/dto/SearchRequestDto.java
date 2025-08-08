package com.example.jobx_api.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchRequestDto extends PaginationDto{

    private String jobCd;

    private String findText;

    private Long memberId;

    private Long searchType;

}
