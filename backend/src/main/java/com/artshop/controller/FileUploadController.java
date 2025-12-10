package com.artshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {
    
    // Upload directory - stores images in both source and target folders
    private static final String SOURCE_UPLOAD_DIR = "src/main/resources/static/uploads/";
    private static final String TARGET_UPLOAD_DIR = "target/classes/static/uploads/";
    
    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Validate file
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body(createErrorResponse("Please select a file to upload"));
            }
            
            // Validate file type
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.badRequest().body(createErrorResponse("Only image files are allowed"));
            }
            
            // Create upload directories if they don't exist
            File sourceUploadDir = new File(SOURCE_UPLOAD_DIR);
            if (!sourceUploadDir.exists()) {
                sourceUploadDir.mkdirs();
            }
            File targetUploadDir = new File(TARGET_UPLOAD_DIR);
            if (!targetUploadDir.exists()) {
                targetUploadDir.mkdirs();
            }
            
            // Generate unique filename
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String uniqueFilename = UUID.randomUUID().toString() + fileExtension;
            
            // Save file to both locations
            Path sourceFilePath = Paths.get(SOURCE_UPLOAD_DIR + uniqueFilename);
            Path targetFilePath = Paths.get(TARGET_UPLOAD_DIR + uniqueFilename);
            Files.copy(file.getInputStream(), sourceFilePath, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(Files.newInputStream(sourceFilePath), targetFilePath, StandardCopyOption.REPLACE_EXISTING);
            
            // Return URL to access the image
            String imageUrl = "/uploads/" + uniqueFilename;
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Image uploaded successfully");
            response.put("imageUrl", imageUrl);
            response.put("filename", uniqueFilename);
            
            return ResponseEntity.ok(response);
            
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createErrorResponse("Failed to upload image: " + e.getMessage()));
        }
    }
    
    private Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", message);
        return response;
    }
}
