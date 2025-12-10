// API Base URL - Use relative path since frontend and backend are on same server
const API_BASE_URL = '/api';

// Shopping Cart
let cart = JSON.parse(localStorage.getItem('cart')) || [];

// Initialize the application
document.addEventListener('DOMContentLoaded', () => {
    loadArtworks();
    displayCart();
    setupEventListeners();
    updateUserDisplay();
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
        document.getElementById('artworks-container').innerHTML = 
            '<p>Error loading artworks. Please try again later.</p>';
    }
}

// Display artworks in the gallery
function displayArtworks(artworks) {
    const container = document.getElementById('artworks-container');
    container.innerHTML = '';

    artworks.forEach(artwork => {
        const card = document.createElement('div');
        card.className = 'artwork-card';
        card.innerHTML = `
            <img src="${artwork.imageUrl}" alt="${artwork.title}" class="artwork-image">
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
    alert(`${title} added to cart!`);
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
    const totalItems = cart.reduce((sum, item) => sum + item.quantity, 0);
    document.getElementById('cart-count').textContent = totalItems;
}

// Display cart items
function displayCart() {
    const cartItemsContainer = document.getElementById('cart-items');
    const totalItemsSpan = document.getElementById('total-items');
    const totalPriceSpan = document.getElementById('total-price');

    if (cart.length === 0) {
        cartItemsContainer.innerHTML = '<p>Your cart is empty.</p>';
        totalItemsSpan.textContent = '0';
        totalPriceSpan.textContent = '0.00';
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

    totalItemsSpan.textContent = totalItems;
    totalPriceSpan.textContent = totalPrice.toFixed(2);
    updateCartCount();
}

// Setup event listeners
function setupEventListeners() {
    const checkoutBtn = document.getElementById('checkout-btn');
    const checkoutForm = document.getElementById('checkout-form');

    checkoutBtn.addEventListener('click', () => {
        if (cart.length === 0) {
            alert('Your cart is empty. Add some artworks first!');
            return;
        }
        document.getElementById('checkout').scrollIntoView({ behavior: 'smooth' });
    });

    checkoutForm.addEventListener('submit', handleCheckout);
}

// Handle checkout form submission
async function handleCheckout(event) {
    event.preventDefault();

    if (cart.length === 0) {
        alert('Your cart is empty!');
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
        totalAmount: cart.reduce((sum, item) => sum + (item.price * item.quantity), 0)
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
        alert('Order placed successfully! Order ID: ' + result.id);
        
        // Clear cart and reset form
        cart = [];
        saveCart();
        event.target.reset();
        displayCart();
        
        // Scroll to top
        window.scrollTo({ top: 0, behavior: 'smooth' });
    } catch (error) {
        console.error('Error placing order:', error);
        alert('Failed to place order. Please try again.');
    }
}

// Initial cart count update
updateCartCount();

// Update user display in navbar
function updateUserDisplay() {
    const user = getCurrentUser();
    const navMenu = document.querySelector('.nav-menu');
    
    if (user && navMenu) {
        // Remove login/signup links if they exist
        const existingAuthLinks = navMenu.querySelectorAll('.auth-link');
        existingAuthLinks.forEach(link => link.remove());
        
        // Add user info and logout
        const userItem = document.createElement('li');
        userItem.innerHTML = `
            <span style="color: white; margin-right: 10px;">Welcome, ${user.firstName}!</span>
            <button onclick="logout()" class="btn btn-secondary" style="padding: 5px 15px;">Logout</button>
        `;
        navMenu.appendChild(userItem);
    }
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
