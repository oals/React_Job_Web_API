package com.example.jobx_api.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchResponseDto {

    private List<NewsDto> newsList;

    private List<JobDto> jobList;

    private Long totalCount;

    private JobDto jobInfo;
}
