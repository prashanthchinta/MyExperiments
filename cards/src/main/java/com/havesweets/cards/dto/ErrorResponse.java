package com.havesweets.cards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String apiPath;
    private int statusCode;
    private String message;
    private LocalDateTime errorTime;

    }
