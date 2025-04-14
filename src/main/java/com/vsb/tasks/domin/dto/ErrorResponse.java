package com.vsb.tasks.domin.dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
