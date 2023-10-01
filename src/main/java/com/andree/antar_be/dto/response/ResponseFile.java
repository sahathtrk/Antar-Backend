package com.andree.antar_be.dto.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseFile {
    String fileName;
    String urlFile;
}
