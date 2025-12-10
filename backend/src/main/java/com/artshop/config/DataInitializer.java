package com.artshop.config;

import com.artshop.model.Artwork;
import com.artshop.model.User;
import com.artshop.repository.ArtworkRepository;
import com.artshop.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(ArtworkRepository artworkRepository, UserRepository userRepository) {
        return args -> {
            // Only initialize if database is empty
            if (userRepository.count() > 0) {
                System.out.println("✓ Database already contains data. Skipping initialization.");
                return;
            }
            
            System.out.println("✓ Initializing database with sample data...");
            
            // Create sample artists
            User artist1 = new User();
            artist1.setFirstName("Vincent");
            artist1.setLastName("van Gogh");
            artist1.setEmail("vincent@artshop.com");
            artist1.setPassword("password123");
            artist1.setRole("ARTIST");
            artist1.setBio("Post-Impressionist painter");
            artist1 = userRepository.save(artist1);
            
            User artist2 = new User();
            artist2.setFirstName("Salvador");
            artist2.setLastName("Dali");
            artist2.setEmail("salvador@artshop.com");
            artist2.setPassword("password123");
            artist2.setRole("ARTIST");
            artist2.setBio("Surrealist master");
            artist2 = userRepository.save(artist2);
            
            User artist3 = new User();
            artist3.setFirstName("Edvard");
            artist3.setLastName("Munch");
            artist3.setEmail("edvard@artshop.com");
            artist3.setPassword("password123");
            artist3.setRole("ARTIST");
            artist3.setBio("Expressionist painter");
            artist3 = userRepository.save(artist3);
            
            // Create sample customer
            User customer = new User();
            customer.setFirstName("John");
            customer.setLastName("Doe");
            customer.setEmail("john@example.com");
            customer.setPassword("password123");
            customer.setRole("CUSTOMER");
            userRepository.save(customer);
            
            // Create sample artworks
            Artwork artwork1 = new Artwork();
            artwork1.setTitle("Starry Night");
            artwork1.setDescription("Beautiful night sky painting");
            artwork1.setPrice(250.00);
            artwork1.setArtist("Vincent van Gogh");
            artwork1.setArtistId(artist1.getId());
            artwork1.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/e/ea/Van_Gogh_-_Starry_Night_-_Google_Art_Project.jpg/300px-Van_Gogh_-_Starry_Night_-_Google_Art_Project.jpg");
            artwork1.setCategory("Post-Impressionism");
            artwork1.setStock(5);
            artwork1.setCreatedAt(System.currentTimeMillis());
            artworkRepository.save(artwork1);
            
            Artwork artwork2 = new Artwork();
            artwork2.setTitle("The Persistence of Memory");
            artwork2.setDescription("Surrealist masterpiece");
            artwork2.setPrice(350.00);
            artwork2.setArtist("Salvador Dali");
            artwork2.setArtistId(artist2.getId());
            artwork2.setImageUrl("https://upload.wikimedia.org/wikipedia/en/thumb/d/dd/The_Persistence_of_Memory.jpg/300px-The_Persistence_of_Memory.jpg");
            artwork2.setCategory("Surrealism");
            artwork2.setStock(3);
            artwork2.setCreatedAt(System.currentTimeMillis());
            artworkRepository.save(artwork2);
            
            Artwork artwork3 = new Artwork();
            artwork3.setTitle("The Scream");
            artwork3.setDescription("Expressionist masterpiece");
            artwork3.setPrice(300.00);
            artwork3.setArtist("Edvard Munch");
            artwork3.setArtistId(artist3.getId());
            artwork3.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/Edvard_Munch%2C_1893%2C_The_Scream%2C_oil%2C_tempera_and_pastel_on_cardboard%2C_91_x_73_cm%2C_National_Gallery_of_Norway.jpg/300px-Edvard_Munch%2C_1893%2C_The_Scream%2C_oil%2C_tempera_and_pastel_on_cardboard%2C_91_x_73_cm%2C_National_Gallery_of_Norway.jpg");
            artwork3.setCategory("Expressionism");
            artwork3.setStock(4);
            artwork3.setCreatedAt(System.currentTimeMillis());
            artworkRepository.save(artwork3);
            
            Artwork artwork4 = new Artwork();
            artwork4.setTitle("The Kiss");
            artwork4.setDescription("Iconic Art Nouveau painting");
            artwork4.setPrice(280.00);
            artwork4.setArtist("Gustav Klimt");
            artwork4.setArtistId(artist1.getId());
            artwork4.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f3/Gustav_Klimt_016.jpg/300px-Gustav_Klimt_016.jpg");
            artwork4.setCategory("Art Nouveau");
            artwork4.setStock(6);
            artwork4.setCreatedAt(System.currentTimeMillis());
            artworkRepository.save(artwork4);
            
            Artwork artwork5 = new Artwork();
            artwork5.setTitle("Girl with a Pearl Earring");
            artwork5.setDescription("Dutch Golden Age painting");
            artwork5.setPrice(320.00);
            artwork5.setArtist("Johannes Vermeer");
            artwork5.setArtistId(artist2.getId());
            artwork5.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/1665_Girl_with_a_Pearl_Earring.jpg/300px-1665_Girl_with_a_Pearl_Earring.jpg");
            artwork5.setCategory("Baroque");
            artwork5.setStock(2);
            artwork5.setCreatedAt(System.currentTimeMillis());
            artworkRepository.save(artwork5);
            
            Artwork artwork6 = new Artwork();
            artwork6.setTitle("The Great Wave");
            artwork6.setDescription("Famous Japanese woodblock print");
            artwork6.setPrice(200.00);
            artwork6.setArtist("Hokusai");
            artwork6.setArtistId(artist3.getId());
            artwork6.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0a/The_Great_Wave_off_Kanagawa.jpg/300px-The_Great_Wave_off_Kanagawa.jpg");
            artwork6.setCategory("Ukiyo-e");
            artwork6.setStock(8);
            artwork6.setCreatedAt(System.currentTimeMillis());
            artworkRepository.save(artwork6);
            
            System.out.println("✓ Database initialized with sample data:");
            System.out.println("  - " + userRepository.count() + " users created");
            System.out.println("  - " + artworkRepository.count() + " artworks created");
            System.out.println("  - Sample login: john@example.com / password123");
            System.out.println("  - Artist login: vincent@artshop.com / password123");
        };
    }
}
