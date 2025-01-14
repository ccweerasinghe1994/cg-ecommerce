package com.cgnexus.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {

    public String uploadImage(String path, MultipartFile image) throws IOException {
        String originalFilename = image.getOriginalFilename();
        if (originalFilename == null) {
            throw new IllegalArgumentException("File name cannot be null");
        }

        // Generate unique filename to avoid conflicts
        String filename = System.currentTimeMillis() + "-" + originalFilename.replaceAll("\\s+", "_");
        Path filePath = Paths.get(path + filename);

        // Create directories if they don't exist
        Files.createDirectories(filePath.getParent());

        // Copy file to destination
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filename;
    }
}
