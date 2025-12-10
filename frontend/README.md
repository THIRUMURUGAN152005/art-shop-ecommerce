# Frontend Documentation

## Overview

The Art Shop frontend is a responsive web application built with vanilla HTML, CSS, and JavaScript. It provides an intuitive interface for browsing artwork, managing shopping carts, and completing orders.

## File Structure

```
frontend/
├── index.html                    # Main HTML file
├── assets/
│   ├── css/
│   │   └── style.css            # All styling and responsive design
│   ├── js/
│   │   └── main.js              # Application logic and API calls
│   └── images/                  # Artwork images directory
└── README.md                    # This file
```

## Features

### Gallery
- Display all artworks from backend API
- Show artwork details: title, price, description, image
- Responsive grid layout that adapts to screen size
- Hover effects for better UX

### Shopping Cart
- Add/remove items to cart
- Update quantities
- View cart summary with totals
- Persistent storage using LocalStorage
- Cart count in navigation bar

### Checkout
- Customer information form
- Delivery address fields
- Payment information collection
- Form validation
- Order confirmation with ID

### Responsive Design
- Mobile-first approach
- Adapts to all screen sizes
- Touch-friendly buttons
- Readable on all devices

## Technologies Used

### HTML
- Semantic markup
- Form elements
- Accessible structure

### CSS
- CSS Grid for gallery layout
- Flexbox for layout alignment
- Media queries for responsive design
- CSS transitions for smooth interactions
- CSS variables for consistent styling

### JavaScript 
- Fetch API for HTTP requests
- DOM manipulation
- Event listeners
- LocalStorage API
- Promises and async/await

## File Details

### index.html

**Structure:**
- Navigation bar with cart counter
- Hero section for branding
- Art gallery section with dynamic content
- Shopping cart display
- Checkout form
- Footer

**Key Elements:**
```html
<!-- Navigation -->
<nav class="navbar"> ... </nav>

<!-- Hero Banner -->
<section class="hero"> ... </section>

<!-- Art Gallery -->
<section id="gallery" class="gallery-section"> ... </section>

<!-- Shopping Cart -->
<section id="cart" class="cart-section"> ... </section>

<!-- Checkout Form -->
<section id="checkout" class="checkout-section"> ... </section>

<!-- Footer -->
<footer class="footer"> ... </footer>
```

### style.css

**Sections:**
1. **General Styles** - Base styles, typography, colors
2. **Navigation** - Navbar styling with animations
3. **Hero Section** - Banner with gradient background
4. **Gallery Section** - Grid layout for artworks
5. **Artwork Cards** - Individual artwork styling with hover effects
6. **Buttons** - Multiple button styles (primary, secondary, success, danger)
7. **Cart Section** - Shopping cart layout
8. **Checkout Section** - Form styling
9. **Footer** - Footer styling
10. **Responsive Design** - Media queries for mobile devices

**Color Scheme:**
- Primary: #667eea (Purple)
- Secondary: #764ba2 (Dark Purple)
- Accent: #e74c3c (Red)
- Text: #2c3e50 (Dark Blue)
- Light: #f5f5f5 (Light Gray)
- White: #ffffff

### main.js

**Global Variables:**
```javascript
const API_BASE_URL = 'http://localhost:8080/api'
let cart = [] // Shopping cart items
```

**Key Functions:**

1. **loadArtworks()**
   - Fetches artworks from backend
   - Handles errors gracefully
   - Displays loading/error states

2. **displayArtworks(artworks)**
   - Renders artwork cards in gallery
   - Creates dynamic HTML elements
   - Adds event listeners for "Add to Cart"

3. **addToCart(id, title, price)**
   - Adds item to cart or increases quantity
   - Saves to LocalStorage
   - Shows confirmation message

4. **removeFromCart(id)**
   - Removes item from cart
   - Updates display

5. **updateQuantity(id, quantity)**
   - Updates item quantity
   - Recalculates totals

6. **displayCart()**
   - Renders cart items
   - Calculates totals
   - Updates cart counter

7. **handleCheckout(event)**
   - Validates form
   - Sends order to backend
   - Handles response and errors

8. **saveCart()**
   - Saves cart to LocalStorage
   - Updates cart count

## API Integration

### Endpoints Used

**Get All Artworks**
```javascript
GET /api/artworks
```

**Create Order**
```javascript
POST /api/orders
Content-Type: application/json
Body: {
    fullName: String,
    email: String,
    address: String,
    city: String,
    postalCode: String,
    cardNumber: String,
    items: Array,
    totalAmount: Number
}
```

## Data Flow

### Loading Artworks
1. Page loads
2. `loadArtworks()` called
3. Fetch data from `/api/artworks`
4. Parse response
5. Call `displayArtworks()` to render

### Adding to Cart
1. User clicks "Add to Cart"
2. `addToCart()` called
3. Item added to cart array
4. `saveCart()` updates LocalStorage
5. `displayCart()` updates UI
6. Confirmation message shown

### Placing Order
1. User fills checkout form
2. User submits form
3. `handleCheckout()` validates
4. Order object created
5. POST request to `/api/orders`
6. Response handled
7. Cart cleared, form reset
8. Confirmation message shown

## Using the Application

### Prerequisites
- Modern web browser (Chrome, Firefox, Safari, Edge)
- Backend API running on localhost:8080
- Internet connection for API calls

### Setup

1. **Start the Backend**
   ```bash
   cd backend
   mvn spring-boot:run
   ```
   Backend runs on `http://localhost:8080`

2. **Open Frontend**
   - Option 1: Open `index.html` directly in browser
   - Option 2: Use a local server (Python):
     ```bash
     cd frontend
     python -m http.server 8000
     ```
     Then visit `http://localhost:8000`

### Usage Steps

1. **Browse Artworks**
   - Scroll through the gallery
   - View artwork details

2. **Add to Cart**
   - Click "Add to Cart" button
   - See cart counter update
   - Can add same artwork multiple times

3. **View Cart**
   - Scroll to shopping cart section
   - See all items and totals
   - Adjust quantities if needed
   - Remove items if desired

4. **Checkout**
   - Click "Proceed to Checkout"
   - Fill in all required information
   - Click "Place Order"
   - See confirmation with Order ID

5. **New Shopping Session**
   - Cart clears automatically after order
   - Start browsing and shopping again

## Browser Compatibility

- **Chrome/Chromium** - Full support
- **Firefox** - Full support
- **Safari** - Full support (iOS 9+)
- **Edge** - Full support
- **IE 11** - Not supported

## Responsive Breakpoints

- **Desktop** - 1200px and above
- **Tablet** - 768px to 1199px
- **Mobile** - Below 768px

## Local Storage

Cart data is stored in browser's LocalStorage:
```javascript
// Stored as JSON string
localStorage['cart'] = JSON.stringify([
    {
        id: 'artwork-1',
        title: 'Title',
        price: 99.99,
        quantity: 1
    }
])
```

## Customization

### Changing Colors
Edit the CSS color scheme in `style.css`:
```css
/* Primary color */
--primary-color: #667eea;

/* Secondary color */
--secondary-color: #764ba2;

/* Accent color */
--accent-color: #e74c3c;
```

### Adding Artwork Images
Place images in `assets/images/` directory and update image URLs in artwork data from backend.

### Modifying API Base URL
Change in `main.js`:
```javascript
const API_BASE_URL = 'http://localhost:8080/api';
```

## Performance Optimization

- Uses vanilla JavaScript (no framework overhead)
- CSS Grid for efficient layouts
- LocalStorage for client-side caching
- Minimal external dependencies
- Lazy-loaded images recommended

## Accessibility Features

- Semantic HTML structure
- Form labels associated with inputs
- Keyboard navigation support
- Color contrast compliant
- Responsive text sizing

## Troubleshooting

### Artworks not loading
- Check backend is running on localhost:8080
- Check browser console for errors (F12)
- Verify CORS is configured in backend

### Cart not persisting
- Check if LocalStorage is enabled
- Try clearing browser cache
- Check browser's privacy settings

### Checkout not working
- Verify all form fields are filled
- Check backend API is running
- Check browser console for error details

### Styling looks broken
- Clear browser cache (Ctrl+Shift+Delete)
- Hard refresh page (Ctrl+F5)
- Check style.css is loading (Network tab in DevTools)

## Future Enhancements

- User authentication
- Wishlist functionality
- Product reviews and ratings
- Advanced filtering and search
- Product recommendations
- Order history
- Multiple payment methods
- Email notifications
- Product comparison
- Live chat support

## Support

For issues or questions, refer to the main README.md or backend documentation.
