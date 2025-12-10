// API Base URL
const API_BASE_URL = '/api';

// Shopping Cart
let cart = JSON.parse(localStorage.getItem('cart')) || [];

// Initialize the application
document.addEventListener('DOMContentLoaded', () => {
    // Only call functions if elements exist
    if (document.getElementById('artworks-container')) {
        loadArtworks();
    }
    if (document.getElementById('cart-items')) {
        displayCart();
    }
    if (document.getElementById('checkout-form')) {
        const form = document.getElementById('checkout-form');
        form.addEventListener('submit', handleCheckout);
    }
    updateCartCount();
});

// Fetch artworks from backend API
async function loadArtworks() {
    try {
        const response = await fetch(`${API_BASE_URL}/artworks`);
        if (!response.ok) throw new Error('Failed to fetch artworks');
        
        const artworks = await response.json();
        displayArtworks(artworks);
    } catch (error) {
        console.error('Error loading artworks:', error);
        const container = document.getElementById('artworks-container');
        if (container) {
            container.innerHTML = '<p>Error loading artworks. Please try again later.</p>';
        }
    }
}

// Display artworks in the gallery
function displayArtworks(artworks) {
    const container = document.getElementById('artworks-container');
    if (!container) return; // Exit if element doesn't exist
    
    container.innerHTML = '';

    if (artworks.length === 0) {
        container.innerHTML = '<p style="text-align: center; color: #7f8c8d; padding: 2rem;">No artworks available yet. Check back soon!</p>';
        return;
    }

    artworks.forEach(artwork => {
        const card = document.createElement('div');
        card.className = 'artwork-card';
        
        // Handle image URL - support both relative and absolute paths
        let imageUrl = artwork.imageUrl;
        if (imageUrl && !imageUrl.startsWith('http') && !imageUrl.startsWith('/')) {
            imageUrl = '/' + imageUrl;
        }
        
        card.innerHTML = `
            <img src="${imageUrl}" alt="${artwork.title}" class="artwork-image" onerror="this.src='https://via.placeholder.com/300x200?text=No+Image'">
            <div class="artwork-details">
                <h3 class="artwork-title">${artwork.title}</h3>
                <p class="artwork-price">$${artwork.price.toFixed(2)}</p>
                <p class="artwork-description">${artwork.description}</p>
                <button class="btn btn-primary" onclick="addToCart('${artwork.id}', '${artwork.title}', ${artwork.price})">
                    Add to Cart
                </button>
            </div>
        `;
        container.appendChild(card);
    });
}

// Add artwork to cart
function addToCart(id, title, price) {
    const existingItem = cart.find(item => item.id === id);
    
    if (existingItem) {
        existingItem.quantity += 1;
    } else {
        cart.push({
            id: id,
            title: title,
            price: price,
            quantity: 1
        });
    }
    
    saveCart();
    displayCart();
    showMessage(`${title} added to cart!`, 'success');
}

// Remove item from cart
function removeFromCart(id) {
    cart = cart.filter(item => item.id !== id);
    saveCart();
    displayCart();
}

// Update item quantity in cart
function updateQuantity(id, quantity) {
    const item = cart.find(item => item.id === id);
    if (item) {
        item.quantity = Math.max(1, parseInt(quantity));
        saveCart();
        displayCart();
    }
}

// Save cart to localStorage
function saveCart() {
    localStorage.setItem('cart', JSON.stringify(cart));
    updateCartCount();
}

// Update cart count in navbar
function updateCartCount() {
    const cartCountElement = document.getElementById('cart-count');
    if (cartCountElement) {
        const totalItems = cart.reduce((sum, item) => sum + item.quantity, 0);
        cartCountElement.textContent = totalItems;
    }
}

// Display cart items
function displayCart() {
    const cartItemsContainer = document.getElementById('cart-items');
    if (!cartItemsContainer) return;
    
    const totalItemsSpan = document.getElementById('total-items');
    const totalPriceSpan = document.getElementById('total-price');

    if (cart.length === 0) {
        cartItemsContainer.innerHTML = '<p>Your cart is empty.</p>';
        if (totalItemsSpan) totalItemsSpan.textContent = '0';
        if (totalPriceSpan) totalPriceSpan.textContent = '0.00';
        return;
    }

    cartItemsContainer.innerHTML = '';
    let totalItems = 0;
    let totalPrice = 0;

    cart.forEach(item => {
        totalItems += item.quantity;
        totalPrice += item.price * item.quantity;

        const cartItemElement = document.createElement('div');
        cartItemElement.className = 'cart-item';
        cartItemElement.innerHTML = `
            <div class="cart-item-details">
                <div class="cart-item-title">${item.title}</div>
                <div class="cart-item-price">$${item.price.toFixed(2)} each</div>
            </div>
            <div class="cart-item-quantity">
                <input type="number" value="${item.quantity}" min="1" 
                       onchange="updateQuantity('${item.id}', this.value)" 
                       style="width: 60px; padding: 5px;">
            </div>
            <div>
                <strong>$${(item.price * item.quantity).toFixed(2)}</strong>
            </div>
            <button class="btn btn-danger" onclick="removeFromCart('${item.id}')">Remove</button>
        `;
        cartItemsContainer.appendChild(cartItemElement);
    });

    if (totalItemsSpan) totalItemsSpan.textContent = totalItems;
    if (totalPriceSpan) totalPriceSpan.textContent = totalPrice.toFixed(2);
    updateCartCount();
}

// Handle checkout form submission
async function handleCheckout(event) {
    event.preventDefault();

    if (cart.length === 0) {
        showMessage('Your cart is empty!', 'error');
        return;
    }

    const formData = new FormData(event.target);
    
    const order = {
        fullName: formData.get('fullName'),
        email: formData.get('email'),
        address: formData.get('address'),
        city: formData.get('city'),
        postalCode: formData.get('postalCode'),
        cardNumber: formData.get('cardNumber'),
        items: cart,
        totalAmount: cart.reduce((sum, item) => sum + (item.price * item.quantity), 0),
        status: 'PENDING',
        createdAt: Date.now()
    };

    try {
        const response = await fetch(`${API_BASE_URL}/orders`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(order)
        });

        if (!response.ok) throw new Error('Failed to place order');

        const result = await response.json();
        
        // Store order ID and redirect to confirmation page
        localStorage.setItem('lastOrderId', result.id);
        
        // Clear cart
        cart = [];
        saveCart();
        
        // Redirect to order confirmation page
        window.location.href = 'order-confirmation.html';
        
    } catch (error) {
        console.error('Error placing order:', error);
        showMessage('Failed to place order. Please try again.', 'error');
    }
}

// Show message function
function showMessage(message, type) {
    // Create message element
    const messageDiv = document.createElement('div');
    messageDiv.className = `alert alert-${type}`;
    messageDiv.textContent = message;
    messageDiv.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        padding: 15px 20px;
        background: ${type === 'success' ? '#4CAF50' : '#f44336'};
        color: white;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.2);
        z-index: 10000;
        animation: slideIn 0.3s ease-out;
    `;
    
    document.body.appendChild(messageDiv);
    
    // Auto-remove after 3 seconds
    setTimeout(() => {
        messageDiv.style.animation = 'slideOut 0.3s ease-out';
        setTimeout(() => messageDiv.remove(), 300);
    }, 3000);
}

// Get current user from storage
function getCurrentUser() {
    const userStr = localStorage.getItem('user') || sessionStorage.getItem('user');
    return userStr ? JSON.parse(userStr) : null;
}

// Logout function
function logout() {
    localStorage.removeItem('user');
    localStorage.removeItem('userId');
    localStorage.removeItem('userEmail');
    localStorage.removeItem('userName');
    sessionStorage.clear();
    window.location.href = 'login.html';
}

// Initial cart count update
updateCartCount();
