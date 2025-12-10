package com.artshop.controller;

import com.artshop.model.Artwork;
import com.artshop.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/artist")
public class ArtistController {
    
    @Autowired
    private ArtworkRepository artworkRepository;
    
    // Upload new artwork
    @PostMapping("/artworks")
    public ResponseEntity<?> uploadArtwork(@RequestBody Artwork artwork) {
        try {
            artwork.setCreatedAt(System.currentTimeMillis());
            Artwork savedArtwork = artworkRepository.save(artwork);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Artwork uploaded successfully");
            response.put("artwork", savedArtwork);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Failed to upload artwork: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Get artworks by artist ID
    @GetMapping("/artworks/{artistId}")
    public ResponseEntity<?> getArtistArtworks(@PathVariable String artistId) {
        try {
            List<Artwork> artworks = artworkRepository.findAll().stream()
                    .filter(artwork -> artistId.equals(artwork.getArtistId()))
                    .toList();
            return ResponseEntity.ok(artworks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching artworks");
        }
    }
    
    // Update artwork
    @PutMapping("/artworks/{id}")
    public ResponseEntity<?> updateArtwork(@PathVariable String id, @RequestBody Artwork artwork) {
        try {
            return artworkRepository.findById(id)
                    .map(existingArtwork -> {
                        existingArtwork.setTitle(artwork.getTitle());
                        existingArtwork.setDescription(artwork.getDescription());
                        existingArtwork.setPrice(artwork.getPrice());
                        existingArtwork.setImageUrl(artwork.getImageUrl());
                        existingArtwork.setCategory(artwork.getCategory());
                        existingArtwork.setStock(artwork.getStock());
                        
                        Artwork updated = artworkRepository.save(existingArtwork);
                        Map<String, Object> response = new HashMap<>();
                        response.put("success", true);
                        response.put("message", "Artwork updated successfully");
                        response.put("artwork", updated);
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Failed to update artwork");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Delete artwork
    @DeleteMapping("/artworks/{id}")
    public ResponseEntity<?> deleteArtwork(@PathVariable String id) {
        try {
            artworkRepository.deleteById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Artwork deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Failed to delete artwork");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
