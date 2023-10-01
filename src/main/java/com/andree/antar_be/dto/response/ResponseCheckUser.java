package com.andree.antar_be.dto.response;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ResponseCheckUser {
    boolean isRegister;
    @Nullable
    String userId;
}
