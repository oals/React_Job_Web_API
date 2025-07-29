package com.example.jobx_api.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchResponseDto {


    List<JobDto> jobList;

    JobDto jobInfo;
}
