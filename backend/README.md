# Art Shop Backend Documentation

## Overview

The Art Shop backend is a Spring Boot REST API that manages artwork inventory and customer orders. It provides endpoints for browsing artworks, creating orders, and managing product information stored in MongoDB.

## Architecture

The backend follows a layered architecture pattern:

```
Controller Layer (REST Endpoints)
    ↓
Service Layer (Business Logic)
    ↓
Repository Layer (Data Access)
    ↓
Database (MongoDB)
```

## Project Structure

```
src/
├── main/
│   ├── java/com/artshop/
│   │   ├── ArtShopApplication.java      # Spring Boot application class
│   │   ├── model/                       # Data models
│   │   │   ├── Artwork.java             # Artwork entity
│   │   │   ├── Order.java               # Order entity
│   │   │   └── OrderItem.java           # Order line item entity
│   │   ├── controller/                  # REST controllers
│   │   │   ├── ArtworkController.java   # Artwork API endpoints
│   │   │   └── OrderController.java     # Order API endpoints
│   │   ├── service/                     # Service layer
│   │   │   ├── ArtworkService.java      # Artwork business logic
│   │   │   └── OrderService.java        # Order business logic
│   │   └── repository/                  # Data access layer
│   │       ├── ArtworkRepository.java   # Artwork database queries
│   │       └── OrderRepository.java     # Order database queries
│   └── resources/
│       └── application.properties       # Application configuration
└── test/                                # Unit tests
```

## Data Models

### Artwork Model
```java
{
    id: String,
    title: String,
    description: String,
    price: Double,
    artist: String,
    imageUrl: String,
    category: String,
    stock: Integer,
    createdAt: Long
}
```

### Order Model
```java
{
    id: String,
    fullName: String,
    email: String,
    address: String,
    city: String,
    postalCode: String,
    cardNumber: String (masked),
    items: List<OrderItem>,
    totalAmount: Double,
    status: String,
    createdAt: Long
}
```

### OrderItem Model
```java
{
    id: String,
    title: String,
    price: Double,
    quantity: Integer
}
```

## API Endpoints

### Artworks API

#### Get All Artworks
```
GET /api/artworks
Response: 200 OK
Body: [{ artwork objects }]
```

#### Get Artwork by ID
```
GET /api/artworks/{id}
Response: 200 OK or 404 Not Found
Body: { artwork object }
```

#### Get Artworks by Category
```
GET /api/artworks/category/{category}
Response: 200 OK
Body: [{ artwork objects }]
```

#### Get Artworks by Artist
```
GET /api/artworks/artist/{artist}
Response: 200 OK
Body: [{ artwork objects }]
```

#### Search Artworks
```
GET /api/artworks/search?title={searchTerm}
Response: 200 OK
Body: [{ artwork objects matching search }]
```

#### Create Artwork
```
POST /api/artworks
Content-Type: application/json
Body: { artwork object }
Response: 201 Created
Body: { created artwork with id }
```

#### Update Artwork
```
PUT /api/artworks/{id}
Content-Type: application/json
Body: { updated artwork data }
Response: 200 OK
Body: { updated artwork }
```

#### Delete Artwork
```
DELETE /api/artworks/{id}
Response: 204 No Content
```

### Orders API

#### Get All Orders
```
GET /api/orders
Response: 200 OK
Body: [{ order objects }]
```

#### Get Order by ID
```
GET /api/orders/{id}
Response: 200 OK or 404 Not Found
Body: { order object }
```

#### Get Orders by Email
```
GET /api/orders/email/{email}
Response: 200 OK
Body: [{ order objects for email }]
```

#### Get Orders by Status
```
GET /api/orders/status/{status}
Response: 200 OK
Body: [{ order objects with status }]
```

#### Create Order
```
POST /api/orders
Content-Type: application/json
Body: { order object }
Response: 201 Created
Body: { created order with id }
```

#### Update Order
```
PUT /api/orders/{id}
Content-Type: application/json
Body: { updated order data }
Response: 200 OK
Body: { updated order }
```

#### Delete Order
```
DELETE /api/orders/{id}
Response: 204 No Content
```

## Configuration

### Application Properties

Located in `src/main/resources/application.properties`:

```properties
# Application name
spring.application.name=artshop-backend

# MongoDB Configuration
spring.data.mongodb.uri=mongodb://localhost:27017/artshop

# Server Configuration
server.port=8080

# Logging
logging.level.root=INFO
logging.level.com.artshop=DEBUG
```

### CORS Configuration

CORS is configured in `ArtShopApplication.java` to allow:
- Origins: `http://localhost:3000`, `http://localhost`, `http://127.0.0.1`
- Methods: GET, POST, PUT, DELETE, OPTIONS
- All headers allowed
- Credentials allowed
- Max age: 3600 seconds

## Running the Application

### Prerequisites
- Java 17+
- Maven 3.8.1+
- MongoDB 4.4+ running on localhost:27017

### Build and Run

```bash
# Navigate to backend directory
cd backend

# Clean and build
mvn clean install

# Run the application
mvn spring-boot:run

# Or package and run JAR
mvn package
java -jar target/artshop-backend-1.0.0.jar
```

Server will start on `http://localhost:8080`

## Testing

### Using cURL

```bash
# Get all artworks
curl -X GET http://localhost:8080/api/artworks

# Create new artwork
curl -X POST http://localhost:8080/api/artworks \
  -H "Content-Type: application/json" \
  -d '{"title":"Test Art","price":99.99,"artist":"Test Artist"}'

# Create order
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{"fullName":"John Doe","email":"john@example.com","address":"123 Main St","city":"NYC","postalCode":"10001","cardNumber":"1234567890123456","items":[],"totalAmount":99.99}'
```

### Using Postman

1. Import the API endpoints into Postman
2. Set base URL to `http://localhost:8080`
3. Test each endpoint with appropriate request bodies

## Security Considerations

- Credit card numbers are masked after first 4 digits
- CORS is configured for specific origins
- Input validation should be added for production
- HTTPS should be used for production deployment
- API authentication/authorization should be implemented
- Rate limiting should be configured

## Future Improvements

- Add Spring Security for authentication
- Implement JWT tokens
- Add request validation with Hibernate Validator
- Add Swagger/OpenAPI documentation
- Implement pagination for large result sets
- Add service layer exception handling
- Implement transaction management
- Add caching with Redis
- Implement audit logging
- Add API rate limiting

## Dependencies

- Spring Boot 3.1.5
- Spring Data MongoDB
- Lombok
- Spring Validation
- JUnit 5

## Troubleshooting

### MongoDB Connection Issues
```
Error: com.mongodb.MongoSocketOpenException
Solution: Ensure MongoDB is running on localhost:27017
```

### CORS Errors
```
Error: Access to XMLHttpRequest blocked by CORS policy
Solution: Check CORS configuration in ArtShopApplication.java
```

### Port Already in Use
```
Error: Port 8080 already in use
Solution: Change server.port in application.properties or kill process on port 8080
```

## Support

For issues or questions, please refer to the main README.md or contact the development team.
