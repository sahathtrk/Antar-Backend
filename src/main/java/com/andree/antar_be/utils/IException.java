package com.andree.antar_be.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class IException extends Exception {
    String code;
    String message;
    int status;
}
