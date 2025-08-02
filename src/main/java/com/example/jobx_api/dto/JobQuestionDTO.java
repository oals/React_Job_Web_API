package com.example.jobx_api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobQuestionDTO {


    private String category;

    private String question;

    private Long score;

}
