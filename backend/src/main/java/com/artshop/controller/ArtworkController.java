package com.artshop.controller;

import com.artshop.model.Artwork;
import com.artshop.service.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/artworks")
public class ArtworkController {
    
    @Autowired
    private ArtworkService artworkService;
    
    @GetMapping
    public ResponseEntity<List<Artwork>> getAllArtworks() {
        return ResponseEntity.ok(artworkService.getAllArtworks());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Artwork> getArtworkById(@PathVariable String id) {
        Optional<Artwork> artwork = artworkService.getArtworkById(id);
        return artwork.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Artwork>> getArtworksByCategory(@PathVariable String category) {
        return ResponseEntity.ok(artworkService.getArtworksByCategory(category));
    }
    
    @GetMapping("/artist/{artist}")
    public ResponseEntity<List<Artwork>> getArtworksByArtist(@PathVariable String artist) {
        return ResponseEntity.ok(artworkService.getArtworksByArtist(artist));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Artwork>> searchArtworks(@RequestParam String title) {
        return ResponseEntity.ok(artworkService.searchArtworks(title));
    }
    
    @PostMapping
    public ResponseEntity<Artwork> createArtwork(@RequestBody Artwork artwork) {
        Artwork createdArtwork = artworkService.createArtwork(artwork);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArtwork);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Artwork> updateArtwork(@PathVariable String id, @RequestBody Artwork artwork) {
        Artwork updatedArtwork = artworkService.updateArtwork(id, artwork);
        if (updatedArtwork != null) {
            return ResponseEntity.ok(updatedArtwork);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtwork(@PathVariable String id) {
        artworkService.deleteArtwork(id);
        return ResponseEntity.noContent().build();
    }
    
}
