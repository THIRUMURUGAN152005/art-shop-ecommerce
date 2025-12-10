// API Base URL - relative path since frontend and backend are on same server
const API_BASE_URL = '/api';

// Handle Signup Form
const signupForm = document.getElementById('signupForm');
if (signupForm) {
    signupForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const firstName = document.getElementById('firstName').value;
        const lastName = document.getElementById('lastName').value;
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;
        
        // Validate passwords match
        if (password !== confirmPassword) {
            showMessage('Passwords do not match!', 'error');
            return;
        }
        
        // Validate password length
        if (password.length < 6) {
            showMessage('Password must be at least 6 characters!', 'error');
            return;
        }
        
        try {
            const response = await fetch(`${API_BASE_URL}/auth/register`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    firstName,
                    lastName,
                    email,
                    password
                })
            });
            
            const data = await response.json();
            
            if (data.success) {
                showMessage('Registration successful! Redirecting to login...', 'success');
                
                // Store user info
                localStorage.setItem('userEmail', email);
                
                // Redirect to login after 2 seconds
                setTimeout(() => {
                    window.location.href = 'login.html';
                }, 2000);
            } else {
                showMessage(data.message || 'Registration failed!', 'error');
            }
        } catch (error) {
            console.error('Error:', error);
            showMessage('An error occurred. Please try again.', 'error');
        }
    });
}

// Handle Login Form
const loginForm = document.getElementById('loginForm');
if (loginForm) {
    loginForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const remember = document.getElementById('remember').checked;
        
        try {
            const response = await fetch(`${API_BASE_URL}/auth/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    email,
                    password
                })
            });
            
            const data = await response.json();
            
            if (data.success) {
                showMessage('Login successful! Redirecting...', 'success');
                
                // Store user info
                const storage = remember ? localStorage : sessionStorage;
                storage.setItem('user', JSON.stringify(data.user));
                storage.setItem('userId', data.user.id);
                storage.setItem('userEmail', data.user.email);
                storage.setItem('userName', data.user.firstName + ' ' + data.user.lastName);
                
                // Redirect to gallery page after 1 second
                setTimeout(() => {
                    window.location.href = 'gallery.html';
                }, 1000);
            } else {
                showMessage(data.message || 'Login failed!', 'error');
            }
        } catch (error) {
            console.error('Error:', error);
            showMessage('An error occurred. Please try again.', 'error');
        }
    });
}

// Show message function
function showMessage(message, type) {
    // Remove existing messages
    const existingMessage = document.querySelector('.message');
    if (existingMessage) {
        existingMessage.remove();
    }
    
    // Create new message
    const messageDiv = document.createElement('div');
    messageDiv.className = `message ${type}`;
    messageDiv.textContent = message;
    
    // Insert at the top of the form
    const form = document.querySelector('.auth-form');
    form.insertBefore(messageDiv, form.firstChild);
    
    // Auto-remove after 5 seconds
    setTimeout(() => {
        messageDiv.remove();
    }, 5000);
}

// Check if user is already logged in
function checkAuth() {
    const user = localStorage.getItem('user') || sessionStorage.getItem('user');
    if (user && (window.location.pathname.includes('login.html') || window.location.pathname.includes('signup.html'))) {
        // User is logged in, redirect to gallery
        window.location.href = 'gallery.html';
    }
}

// Pre-fill email if coming from signup
window.addEventListener('DOMContentLoaded', () => {
    checkAuth();
    
    if (window.location.pathname.includes('login.html')) {
        const savedEmail = localStorage.getItem('userEmail');
        if (savedEmail) {
            document.getElementById('email').value = savedEmail;
        }
    }
});

// Logout function (can be called from other pages)
function logout() {
    localStorage.removeItem('user');
    localStorage.removeItem('userId');
    localStorage.removeItem('userEmail');
    localStorage.removeItem('userName');
    sessionStorage.clear();
    window.location.href = 'login.html';
}

// Get current user
function getCurrentUser() {
    const userStr = localStorage.getItem('user') || sessionStorage.getItem('user');
    return userStr ? JSON.parse(userStr) : null;
}

// Check if user is logged in
function isLoggedIn() {
    return getCurrentUser() !== null;
}
