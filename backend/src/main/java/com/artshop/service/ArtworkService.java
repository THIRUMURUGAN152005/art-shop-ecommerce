package com.artshop.service;

import com.artshop.model.Artwork;
import com.artshop.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ArtworkService {
    
    @Autowired
    private ArtworkRepository artworkRepository;
    
    public List<Artwork> getAllArtworks() {
        return artworkRepository.findAll();
    }
    
    public Optional<Artwork> getArtworkById(String id) {
        return artworkRepository.findById(id);
    }
    
    public List<Artwork> getArtworksByCategory(String category) {
        return artworkRepository.findByCategory(category);
    }
    
    public List<Artwork> getArtworksByArtist(String artist) {
        return artworkRepository.findByArtist(artist);
    }
    
    public List<Artwork> searchArtworks(String title) {
        return artworkRepository.findByTitleContainingIgnoreCase(title);
    }
    
    public Artwork createArtwork(Artwork artwork) {
        artwork.setCreatedAt(System.currentTimeMillis());
        return artworkRepository.save(artwork);
    }
    
    public Artwork updateArtwork(String id, Artwork artwork) {
        Optional<Artwork> existingArtwork = artworkRepository.findById(id);
        if (existingArtwork.isPresent()) {
            artwork.setId(id);
            return artworkRepository.save(artwork);
        }
        return null;
    }
    
    public void deleteArtwork(String id) {
        artworkRepository.deleteById(id);
    }
    
}
