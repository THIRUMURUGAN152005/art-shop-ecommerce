# Art Shop E-Commerce Website

A full-stack e-commerce web application for buying and selling artwork online. Built with HTML, CSS, JavaScript for the frontend, Spring Boot for the backend API, and MongoDB for data persistence.

## Project Structure

```
ecommerceweb/
├── frontend/
│   ├── index.html                 # Main HTML file
│   └── assets/
│       ├── css/
│       │   └── style.css          # Responsive styling
│       ├── js/
│       │   └── main.js            # Frontend logic and API calls
│       └── images/                # Artwork images directory
│
└── backend/
    ├── pom.xml                    # Maven configuration
    ├── src/
    │   ├── main/
    │   │   ├── java/com/artshop/
    │   │   │   ├── ArtShopApplication.java    # Spring Boot entry point
    │   │   │   ├── model/
    │   │   │   │   ├── Artwork.java           # Artwork model
    │   │   │   │   ├── Order.java             # Order model
    │   │   │   │   └── OrderItem.java         # Order item model
    │   │   │   ├── controller/
    │   │   │   │   ├── ArtworkController.java # Artwork REST API
    │   │   │   │   └── OrderController.java   # Order REST API
    │   │   │   ├── service/
    │   │   │   │   ├── ArtworkService.java    # Artwork business logic
    │   │   │   │   └── OrderService.java      # Order business logic
    │   │   │   └── repository/
    │   │   │       ├── ArtworkRepository.java # Artwork data access
    │   │   │       └── OrderRepository.java   # Order data access
    │   │   └── resources/
    │   │       └── application.properties     # Spring Boot configuration
    │   └── test/                  # Unit tests (to be added)
    └── README.md                  # Backend documentation
```

## Features

### Frontend
- **Art Gallery**: Browse artworks with images, titles, prices, and descriptions
- **Shopping Cart**: Add/remove items, update quantities, persistent storage
- **Checkout**: Complete order form with customer details and payment info
- **Responsive Design**: Mobile-friendly interface using CSS Grid and Flexbox
- **Real-time Cart Updates**: Navbar cart counter updates in real-time

### Backend API
- **RESTful Architecture**: Standard HTTP methods (GET, POST, PUT, DELETE)
- **CORS Support**: Enabled for frontend communication
- **MongoDB Integration**: Persistent data storage
- **Security**: Credit card masking for data protection
- **Order Management**: Create, retrieve, and manage orders

### API Endpoints

#### Artworks
- `GET /api/artworks` - Get all artworks
- `GET /api/artworks/{id}` - Get artwork by ID
- `GET /api/artworks/category/{category}` - Get artworks by category
- `GET /api/artworks/artist/{artist}` - Get artworks by artist
- `GET /api/artworks/search?title={title}` - Search artworks by title
- `POST /api/artworks` - Create new artwork
- `PUT /api/artworks/{id}` - Update artwork
- `DELETE /api/artworks/{id}` - Delete artwork

#### Orders
- `GET /api/orders` - Get all orders
- `GET /api/orders/{id}` - Get order by ID
- `GET /api/orders/email/{email}` - Get orders by email
- `GET /api/orders/status/{status}` - Get orders by status
- `POST /api/orders` - Create new order
- `PUT /api/orders/{id}` - Update order
- `DELETE /api/orders/{id}` - Delete order

## Technology Stack

### Frontend
- **HTML5**: Semantic markup
- **CSS3**: Modern styling with Grid and Flexbox
- **JavaScript (ES6+)**: DOM manipulation and Fetch API
- **Local Storage**: Client-side cart persistence

### Backend
- **Java 17**: Programming language
- **Spring Boot 3.1.5**: Web framework
- **Spring Data MongoDB**: Database abstraction
- **Maven**: Build and dependency management

### Database
- **MongoDB**: NoSQL document database

## Prerequisites

- **Java 17** or higher installed
- **Maven 3.8.1** or higher installed
- **MongoDB 4.4** or higher running locally or accessible
- **Node.js** (optional, for running a local server for frontend)
- **Modern web browser** (Chrome, Firefox, Safari, Edge)

## Installation and Setup

### Backend Setup

1. **Install MongoDB**
   ```bash
   # Windows (using Chocolatey)
   choco install mongodb

   # macOS (using Homebrew)
   brew tap mongodb/brew
   brew install mongodb-community

   # Or download from https://www.mongodb.com/try/download/community
   ```

2. **Start MongoDB**
   ```bash
   # Windows
   mongod

   # macOS/Linux
   mongod --dbpath /path/to/data/db
   ```

3. **Build and Run Backend**
   ```bash
   cd backend
   mvn clean install
   mvn spring-boot:run
   ```
   Backend will run on `http://localhost:8080`

### Frontend Setup

1. **Open Frontend in Browser**
   - Navigate to `frontend/index.html` in your web browser
   - OR use a local server:

   ```bash
   # Using Python (Python 3)
   cd frontend
   python -m http.server 3000

   # Using Node.js (if installed)
   cd frontend
   npx http-server -p 3000
   ```
   Frontend will run on `http://localhost:3000`

## Usage

1. **Browse Artworks**
   - Navigate to the Art Gallery section
   - View artwork details (title, price, description, image)

2. **Add to Cart**
   - Click "Add to Cart" button on any artwork
   - View cart in the Shopping Cart section
   - Adjust quantities as needed

3. **Checkout**
   - Click "Proceed to Checkout"
   - Fill in delivery and payment information
   - Click "Place Order" to complete the purchase

4. **Order Confirmation**
   - Receive order ID confirmation
   - Cart will be cleared automatically

## Sample Data

To test the application with sample artworks, you can insert sample data into MongoDB:

```javascript
// Use MongoDB client (mongosh or Compass)
use artshop

db.artworks.insertMany([
    {
        title: "Starry Night",
        description: "A beautiful night sky painting",
        price: 250.00,
        artist: "Vincent van Gogh",
        imageUrl: "assets/images/starry-night.jpg",
        category: "Post-Impressionism",
        stock: 5,
        createdAt: new Date().getTime()
    },
    {
        title: "The Persistence of Memory",
        description: "Surrealist masterpiece with melting clocks",
        price: 350.00,
        artist: "Salvador Dalí",
        imageUrl: "assets/images/persistence.jpg",
        category: "Surrealism",
        stock: 3,
        createdAt: new Date().getTime()
    }
    // Add more artworks as needed
])
```

## Development Notes

### Frontend
- Uses vanilla JavaScript (no frameworks)
- Fetch API for backend communication
- LocalStorage for cart persistence
- Responsive CSS Grid layout

### Backend
- Spring Boot auto-configuration simplifies setup
- Lombok reduces boilerplate code
- MongoDB provides flexible schema design
- Service layer handles business logic
- Repository pattern for data access

## Common Issues and Solutions

### Backend won't connect to MongoDB
- Ensure MongoDB is running
- Check connection string in `application.properties`
- Verify firewall isn't blocking port 27017

### CORS errors when calling API
- CORS is configured in `ArtShopApplication.java`
- Ensure frontend URL is in allowed origins

### Cart not persisting
- Check if browser allows LocalStorage
- Clear browser cache if experiencing issues

## Future Enhancements

- User authentication and registration
- Payment gateway integration (Stripe, PayPal)
- Order tracking and notifications
- Review and rating system
- Advanced search and filtering
- Admin dashboard for inventory management
- Email notifications for orders
- Multiple payment methods support
- Wishlist functionality
- Product recommendations

## API Documentation

Full API documentation can be generated using Swagger/Springdoc-openapi:

```xml
<!-- Add to pom.xml -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.2</version>
</dependency>
```

Then access Swagger UI at: `http://localhost:8080/swagger-ui.html`

## License

This project is open-source and available for educational and commercial use.

## Author

Art Shop Development Team

## Contact

For questions or support, please create an issue in the repository.
