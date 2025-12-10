package com.artshop.repository;

import com.artshop.model.Artwork;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArtworkRepository extends MongoRepository<Artwork, String> {
    
    List<Artwork> findByCategory(String category);
    List<Artwork> findByArtist(String artist);
    List<Artwork> findByTitleContainingIgnoreCase(String title);
    
}
