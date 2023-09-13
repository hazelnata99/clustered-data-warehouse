package com.rinatab.progresssoft.foreignexchangedealsservice.exception;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private String message;
    private String path;
    private Object value;
}
