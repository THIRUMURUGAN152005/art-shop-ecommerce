# Art Shop E-Commerce Platform - Project Description

## 🎨 Art Shop E-Commerce

### Project Overview
A full-stack e-commerce web application for buying and selling artwork online with integrated payment gateway and user authentication.

---

## 🔧 Technical Implementation

### Backend Development
• Developed RESTful API backend to manage users, artworks, orders, and payments.
• Implemented Spring Boot with MongoDB for data persistence and scalability.
• Created authentication system with role-based access (Customer & Artist).
• Integrated Razorpay payment gateway for secure online transactions.
• Tools used: Java 17, Spring Boot, MongoDB, Maven.

### User Management System
• The system provides three core modules – user authentication (signup/login), artwork management (CRUD operations), and order processing, each connected to MongoDB collections.
• Role-based access control with Customer and Artist roles.
• Secure password handling and session management.
• Real-time cart synchronization between frontend and backend.

### Payment Integration
• Integrated Razorpay payment gateway with test and production modes.
• Implemented secure payment processing with order confirmation.
• Payment details stored with order information in MongoDB.
• Support for multiple payment methods (Cards, UPI, Net Banking, Wallets).

### Artist Dashboard
• Artists can upload and manage their artworks with images.
• File upload functionality with 10MB size limit.
• Real-time artwork listing and inventory management.
• Category-based artwork organization.

### Shopping Cart & Checkout
• Persistent shopping cart using browser localStorage.
• Real-time cart updates and quantity management.
• Complete checkout flow with delivery information.
• Order confirmation and tracking system.

### Database Architecture
• MongoDB collections: users, artworks, orders.
• Embedded MongoDB for development with permanent storage option.
• Data models with proper relationships and indexing.
• Sample data initialization for testing.

### Frontend Development
• Built responsive UI with HTML5, CSS3, and vanilla JavaScript.
• Dynamic content rendering with Fetch API.
• Category-based filtering and search functionality.
• Mobile-friendly design using CSS Grid and Flexbox.

### Full-Stack Integration
• Built full-stack project with real-time frontend-backend integration.
• RESTful API endpoints for all CRUD operations.
• CORS configuration for cross-origin requests.
• Real-time data synchronization between client and server.

### Tools & Technologies Used
• **Backend:** Java 17, Spring Boot 3.1.5, Spring Data MongoDB
• **Frontend:** HTML5, CSS3, JavaScript (ES6+)
• **Database:** MongoDB (Embedded & External)
• **Payment:** Razorpay Payment Gateway
• **Build Tool:** Maven
• **Server:** Apache Tomcat (Embedded)

---

## 📋 Key Features

### For Customers:
• Browse art gallery with category filters
• Add artworks to shopping cart
• Secure checkout with Razorpay payment
• Order history and tracking
• User authentication and profile management

### For Artists:
• Artist dashboard for artwork management
• Upload artworks with images and descriptions
• Set pricing and manage inventory
• View sales and orders
• Artist profile with bio

### Admin Features:
• User management (Customer & Artist)
• Order management and status updates
• Artwork moderation and approval
• Payment transaction tracking

---

## 🎯 Technical Highlights

• **RESTful API Design:** Clean API architecture with proper HTTP methods
• **Security:** Password encryption, CORS configuration, input validation
• **Scalability:** MongoDB for flexible schema and horizontal scaling
• **Payment Security:** Razorpay integration with secure payment handling
• **Responsive Design:** Mobile-first approach with CSS Grid/Flexbox
• **Real-time Updates:** Dynamic content loading without page refresh
• **File Upload:** Image upload with size validation and storage
• **Error Handling:** Comprehensive error handling and user feedback

---

## 📊 Database Schema

### Collections:
1. **Users:** User authentication and profile data
2. **Artworks:** Artwork details, pricing, and inventory
3. **Orders:** Order information with payment details

### Key Fields:
• User: id, firstName, lastName, email, password, role, bio
• Artwork: id, title, description, price, artist, imageUrl, category, stock
• Order: id, fullName, email, address, items, totalAmount, status, paymentId

---

## 🚀 Deployment Ready

• Configured for both development and production environments
• Environment-specific profiles (dev/prod)
• Embedded MongoDB for development
• External MongoDB support for production
• Cloud deployment ready (MongoDB Atlas compatible)

---

## 💡 Learning Outcomes

• Full-stack web development with Java and JavaScript
• RESTful API design and implementation
• Payment gateway integration
• Database design and MongoDB operations
• User authentication and authorization
• File upload and storage management
• Responsive web design
• Real-time frontend-backend communication

---

## 📈 Future Enhancements

• Admin dashboard for platform management
• Advanced search and filtering
• Wishlist functionality
• Review and rating system
• Email notifications
• Social media integration
• Analytics dashboard
• Multi-language support

---

**Project Type:** Full-Stack E-Commerce Web Application
**Duration:** Development project with production-ready features
**Status:** Fully functional with payment integration and user management
