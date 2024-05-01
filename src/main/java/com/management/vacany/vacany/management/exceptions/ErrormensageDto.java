package com.management.vacany.vacany.management.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrormensageDto {

    private String message;
    private String field;
}
