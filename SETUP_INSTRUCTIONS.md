# SETUP_INSTRUCTIONS.md

## Art Shop E-Commerce Platform - Complete Setup Guide

This document provides step-by-step instructions to set up and run the Art Shop e-commerce platform.

## Prerequisites

Before starting, ensure you have the following installed:

### Required Software
- **Java 17** or higher - [Download](https://www.oracle.com/java/technologies/downloads/#java17)
- **Maven 3.8.1** or higher - [Download](https://maven.apache.org/download.cgi)
- **MongoDB 4.4** or higher - [Download](https://www.mongodb.com/try/download/community)
- **Git** (optional) - [Download](https://git-scm.com/)

### Recommended Tools
- **MongoDB Compass** - GUI for MongoDB - [Download](https://www.mongodb.com/products/compass)
- **Postman** - API testing tool - [Download](https://www.postman.com/downloads/)
- **Visual Studio Code** - Code editor - [Download](https://code.visualstudio.com/)

## Installation Steps

### Step 1: Install Java 17

#### Windows
1. Download JDK 17 from Oracle website
2. Run the installer and follow the setup wizard
3. Add Java to PATH (usually done automatically)
4. Verify installation:
   ```bash
   java -version
   javac -version
   ```

#### macOS
```bash
# Using Homebrew
brew install openjdk@17

# Set JAVA_HOME (add to ~/.zprofile or ~/.bash_profile)
export JAVA_HOME=$(brew --prefix openjdk@17)
```

#### Linux
```bash
sudo apt-get install openjdk-17-jdk
java -version
```

### Step 2: Install Maven

#### Windows
1. Download Maven binary zip from apache.org
2. Extract to a folder (e.g., C:\apache-maven)
3. Add Maven bin folder to PATH environment variable
4. Verify:
   ```bash
   mvn -version
   ```

#### macOS
```bash
# Using Homebrew
brew install maven
mvn -version
```

#### Linux
```bash
sudo apt-get install maven
mvn -version
```

### Step 3: Install MongoDB

#### Windows
1. Download MongoDB Community Server installer
2. Run the installer
3. Choose "Complete" installation
4. MongoDB will install as a Windows service
5. Verify MongoDB is running:
   ```bash
   mongosh
   ```

#### macOS
```bash
# Using Homebrew
brew tap mongodb/brew
brew install mongodb-community

# Start MongoDB service
brew services start mongodb-community

# Verify
mongosh
```

#### Linux
```bash
sudo apt-get install mongodb
sudo systemctl start mongodb
mongo --version
```

### Step 4: Verify MongoDB Setup

```bash
# Connect to MongoDB
mongosh

# In MongoDB shell
show databases
use artshop
db.artworks.find()
exit
```

## Project Setup

### Step 1: Extract/Clone Project

If you have a zip file:
```bash
# Extract to your desired location
unzip artshop-ecommerce.zip
cd ecommerceweb
```

Or clone from repository:
```bash
git clone <repository-url>
cd ecommerceweb
```

### Step 2: Backend Setup

#### Navigate to Backend Directory
```bash
cd backend
```

#### Build the Backend
```bash
# Clean and build
mvn clean install

# Or skip tests for faster build
mvn clean install -DskipTests
```

This will:
- Download all dependencies
- Compile Java source code
- Run tests (if not skipped)
- Create JAR file

#### Verify Backend Build
```bash
ls target/
# Should show: artshop-backend-1.0.0.jar and other build artifacts
```

### Step 3: Frontend Setup

No build required! Frontend uses vanilla HTML, CSS, and JavaScript.

Just ensure all files are in place:
```bash
cd ../frontend
ls -la
# Should show: index.html, assets/ folder, README.md
```

## Running the Application

### Step 1: Start MongoDB

Make sure MongoDB is running:

```bash
# Windows (if not auto-starting)
mongod

# macOS
brew services start mongodb-community

# Linux
sudo systemctl start mongodb
```

Verify MongoDB is running:
```bash
mongosh
# Should connect successfully
exit
```

### Step 2: Start Backend API

```bash
cd backend

# Option 1: Using Maven
mvn spring-boot:run

# Option 2: Using Java directly (after building)
java -jar target/artshop-backend-1.0.0.jar
```

Expected output:
```
Started ArtShopApplication in X.XXX seconds
```

Backend will be available at: `http://localhost:8080`

### Step 3: Start Frontend

#### Option A: Direct File Opening
1. Navigate to `frontend/index.html`
2. Right-click and select "Open with" your browser
3. Or drag and drop into browser window

#### Option B: Using Python (Recommended)
```bash
cd frontend

# Python 3
python -m http.server 8000

# Then open browser to http://localhost:8000
```

#### Option C: Using Node.js (if installed)
```bash
cd frontend
npx http-server -p 8000
```

## Verify Everything is Running

### Check Backend API
```bash
# In a new terminal
curl http://localhost:8080/api/artworks
# Should return: [] (empty array initially)
```

### Check Frontend
1. Open `http://localhost:8000` (or localhost:3000) in browser
2. You should see the Art Shop website
3. Gallery section should be visible but empty

## Add Sample Data

To test the application with sample artworks:

### Using MongoDB Shell

```bash
mongosh

# Connect to artshop database
use artshop

# Insert sample artworks
db.artworks.insertMany([
    {
        title: "Starry Night",
        description: "A beautiful night sky painting with vibrant colors",
        price: 250.00,
        artist: "Vincent van Gogh",
        imageUrl: "https://via.placeholder.com/300x200?text=Starry+Night",
        category: "Post-Impressionism",
        stock: 5,
        createdAt: new Date().getTime()
    },
    {
        title: "The Persistence of Memory",
        description: "Surrealist masterpiece with melting clocks",
        price: 350.00,
        artist: "Salvador Dalí",
        imageUrl: "https://via.placeholder.com/300x200?text=Persistence",
        category: "Surrealism",
        stock: 3,
        createdAt: new Date().getTime()
    },
    {
        title: "The Girl with the Pearl Earring",
        description: "Classic portrait with mysterious expression",
        price: 200.00,
        artist: "Johannes Vermeer",
        imageUrl: "https://via.placeholder.com/300x200?text=Pearl+Earring",
        category: "Dutch Golden Age",
        stock: 2,
        createdAt: new Date().getTime()
    },
    {
        title: "The Scream",
        description: "Expressionist masterpiece depicting anxiety",
        price: 300.00,
        artist: "Edvard Munch",
        imageUrl: "https://via.placeholder.com/300x200?text=The+Scream",
        category: "Expressionism",
        stock: 4,
        createdAt: new Date().getTime()
    }
])

# Verify data inserted
db.artworks.find().pretty()

# Exit
exit
```

### Or Using Postman

1. Open Postman
2. Create POST request to `http://localhost:8080/api/artworks`
3. Set header: `Content-Type: application/json`
4. Set body:
```json
{
    "title": "Starry Night",
    "description": "A beautiful night sky painting",
    "price": 250.00,
    "artist": "Vincent van Gogh",
    "imageUrl": "https://via.placeholder.com/300x200?text=Starry+Night",
    "category": "Post-Impressionism",
    "stock": 5
}
```
5. Click Send

## Test the Application

1. **Refresh Frontend** - `http://localhost:8000`
2. **Verify Artworks Display** - Should show sample artworks in gallery
3. **Add to Cart** - Click "Add to Cart" on an artwork
4. **View Cart** - Scroll to cart section, verify item is added
5. **Checkout** - Fill form and test order placement

## Environment Variables (Optional)

To configure custom MongoDB connection, edit `backend/src/main/resources/application.properties`:

```properties
# Default (localhost)
spring.data.mongodb.uri=mongodb://localhost:27017/artshop

# Remote MongoDB (e.g., MongoDB Atlas)
# spring.data.mongodb.uri=mongodb+srv://user:password@cluster.mongodb.net/artshop?retryWrites=true&w=majority
```

## Troubleshooting

### MongoDB Connection Error
```
Error: connect ECONNREFUSED 127.0.0.1:27017
```
**Solution:**
- Verify MongoDB is running: `mongosh`
- Check port 27017 is not blocked by firewall
- Restart MongoDB service

### Port Already in Use
```
Error: Address already in use: /0.0.0.0:8080
```
**Solution:**
```bash
# Find process using port 8080
netstat -ano | findstr :8080  # Windows
lsof -i :8080                 # macOS/Linux

# Kill the process or use different port
# In application.properties: server.port=8081
```

### Frontend Not Loading Data
```
Error: Failed to fetch artworks
```
**Solution:**
- Check backend is running on localhost:8080
- Check CORS configuration
- Open browser console (F12) for error details
- Verify network request in Network tab

### Java Version Mismatch
```
Error: Unsupported class version
```
**Solution:**
```bash
java -version  # Verify Java 17+
mvn clean install  # Rebuild with correct Java version
```

## Project Structure Overview

```
ecommerceweb/
├── frontend/
│   ├── index.html          # Main web page
│   ├── assets/
│   │   ├── css/
│   │   │   └── style.css   # Styling
│   │   └── js/
│   │       └── main.js     # Logic
│   └── README.md
│
└── backend/
    ├── pom.xml             # Maven config
    ├── src/
    │   └── main/
    │       ├── java/com/artshop/
    │       │   ├── ArtShopApplication.java
    │       │   ├── model/        # Data models
    │       │   ├── controller/   # REST APIs
    │       │   ├── service/      # Business logic
    │       │   └── repository/   # Database access
    │       └── resources/
    │           └── application.properties
    └── README.md
```

## Development Workflow

### Making Changes to Backend
1. Edit Java files in `backend/src/main/java`
2. Rebuild: `mvn clean install`
3. Restart: Stop (Ctrl+C) and run `mvn spring-boot:run`

### Making Changes to Frontend
1. Edit HTML, CSS, or JavaScript files
2. Refresh browser (F5 or Ctrl+R)
3. Changes apply immediately

## Next Steps

After successful setup:

1. **Test Thoroughly** - Add items, checkout, verify data in MongoDB
2. **Explore Code** - Read source files to understand architecture
3. **Customize** - Modify colors, add more artworks, extend features
4. **Deploy** - Follow deployment guide for production (coming soon)
5. **Enhance** - Add authentication, payment gateway, etc.

## Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [MongoDB Documentation](https://docs.mongodb.com/)
- [MDN Web Docs](https://developer.mozilla.org/)
- [RESTful API Design](https://restfulapi.net/)

## Support

For issues or questions:
1. Check project README.md
2. Review backend/README.md and frontend/README.md
3. Check browser console for errors (F12)
4. Check MongoDB shell for data issues
5. Verify all services are running

## Success!

If you see the Art Shop website with artworks and can add items to cart and place orders, the setup is complete! 🎉

Happy coding and happy selling! 🎨
