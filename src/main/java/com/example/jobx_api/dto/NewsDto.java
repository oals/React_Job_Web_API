package com.example.jobx_api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsDto {

    private String author;

    private String title;

    private String description;

    private String url;

    private String urlToImage;

    private String publishedAt;

    private String content;

    private String sourceName;

}
