package com.solvd.elasticsearchmicroservice.web.dto;

import lombok.Data;

@Data
public class NoteDto {

    private Long id;
    private String description;
    private String theme;
    private String tag;
    private Long userId;

}
