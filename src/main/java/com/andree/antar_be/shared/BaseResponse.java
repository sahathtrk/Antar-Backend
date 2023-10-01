package com.andree.antar_be.shared;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.http.ResponseEntity;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    @Builder.Default
    Object data = DefaultData.builder().build();

    @Builder.Default
    String message = "Success";

    Object error;

    public static ResponseEntity<Object> responseSuccess(Object data, String message, int status) {
        return ResponseEntity.status(status).body(BaseResponse
                .builder()
                .data(data)
                .message(message)
                .build());
    }

    public static ResponseEntity<Object> responseSuccess(String message, int status) {
        return ResponseEntity.status(status).body(BaseResponse
                .builder()
                .message(message)
                .build());
    }
    public static ResponseEntity<Object> responseSuccess(Object data, String message) {
        return ResponseEntity.status(200).body(BaseResponse
                .builder()
                .data(data)
                .message(message)
                .build());
    }

    public static ResponseEntity<Object> responseSuccess(Object data) {
        return ResponseEntity.status(200).body(BaseResponse
                .builder()
                .data(data)
                .build());
    }

    public static ResponseEntity<Object> responseError(Object error, String message, int code) {
        return ResponseEntity.status(code).body(BaseResponse
                .builder()
                .error(error)
                .message(message)
                .build());
    }


    public static ResponseEntity<Object> responseError(IError error, String message) {
        return ResponseEntity.status(error.code).body(BaseResponse
                .builder()
                .error(error)
                .message(message)
                .build());
    }

    public static ResponseEntity<Object> responseError(Object error) {
        return ResponseEntity.status(500).body(BaseResponse
                .builder()
                .error(error)
                .message("Failed")
                .build());
    }
    public static ResponseEntity<Object> responseError(IError error) {
        return ResponseEntity.status(error.code).body(BaseResponse
                .builder()
                .error(error)
                .message("Failed")
                .build());
    }
}

@Builder
@Setter
@Getter
@Jacksonized
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class DefaultData {
}
