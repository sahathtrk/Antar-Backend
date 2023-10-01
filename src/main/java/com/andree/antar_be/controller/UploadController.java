package com.andree.antar_be.controller;

import com.andree.antar_be.dto.response.ResponseFile;
import com.andree.antar_be.service.UploadService;
import com.andree.antar_be.shared.BaseResponse;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/upload")
public class UploadController {

    private final UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("public")
    public ResponseEntity<Object> uploadPublic(@RequestParam("file") MultipartFile file) {
        ResponseFile responseFile = this.uploadService.uploadFile(file);
        return BaseResponse.responseSuccess(responseFile, "Success to upload", 201);
    }

    @PostMapping("private")
    public ResponseEntity<Object> uploadPrivate(@RequestAttribute("claims") Claims claims, @RequestParam("file") MultipartFile file) {
        ResponseFile responseFile = this.uploadService.uploadFilePrivate(file, claims.get("ID").toString());
        return BaseResponse.responseSuccess(responseFile, "Success to upload", 201);
    }

}
