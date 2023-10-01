package com.andree.antar_be.dto.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseAuth {
    String accessToken;
    String refreshToken;
    int timeExpired;
}
