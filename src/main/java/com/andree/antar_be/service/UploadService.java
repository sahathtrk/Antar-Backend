package com.andree.antar_be.service;

import com.andree.antar_be.dto.response.ResponseFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadService {
    @Value("${upload.path}")
    private String uploadPath;

    @Value("${upload.url}")
    private String uploadUrl;

    public ResponseFile uploadFile(MultipartFile file) {
        try {
            String originalName = file.getOriginalFilename();
            Path root = Paths.get(uploadPath);
            String extension = originalName.substring(originalName.indexOf(".") + 1);
            String fileName = UUID.randomUUID() + "." + extension;
            String urlName = uploadUrl + "/" + fileName;
            Files.copy(file.getInputStream(), root.resolve(fileName));
            return ResponseFile.builder().fileName(fileName).urlFile(urlName).build();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseFile uploadFilePrivate(MultipartFile file, String userID) {
        try {
            String originalName = file.getOriginalFilename();
            Path root = Paths.get(uploadPath);
            String extension = originalName.substring(originalName.indexOf(".") + 1);
            String fileName = UUID.randomUUID() + "." + extension;
            String urlName = uploadUrl + "/" + userID + "/" + fileName;
            Files.createDirectories(root.resolve(userID));
            Files.copy(file.getInputStream(), root.resolve(userID + "/" + fileName));
            return ResponseFile.builder().fileName(userID + "/" + fileName).urlFile(urlName).build();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
