package com.andree.antar_be.shared;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IError{
    @Builder.Default
    String message = "internal server error";

    @Builder.Default
    int code = 500;

    @Builder.Default
    String codeServer = "500000";

    @Builder.Default
    List<Object> stack = new ArrayList<>();
}
