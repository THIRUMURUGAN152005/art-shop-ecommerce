# 🎨 Art Shop - FINAL INSTRUCTIONS

## ✅ EASIEST WAY TO RUN (Recommended)

### Just Double-Click This File:
```
RUN.bat
```

That's it! Wait for "Started ArtShopApplication" message, then open browser.

---

## 🌐 Open in Browser

```
http://localhost:8080
```

---

## 🔐 Login Credentials

**Customer Account:**
- Email: `john@example.com`
- Password: `password123`

**Artist Account:**
- Email: `vincent@artshop.com`
- Password: `password123`

---

## 💳 Test Razorpay Payment

1. Login as customer
2. Add artworks to cart
3. Go to cart page
4. Fill delivery details
5. Click "Pay with Razorpay"

**Test Card Details:**
- Card Number: `4111 1111 1111 1111`
- CVV: `123`
- Expiry: `12/25` (any future date)

---

## 📊 Your Data is Permanent

All data is saved in: `backend/mongodb-data/`

This includes:
- ✅ User accounts
- ✅ Artworks
- ✅ Orders and payments
- ✅ Everything persists across restarts!

---

## 🛑 To Stop the Application

Press `Ctrl + C` in the command window

---

## 🔧 Alternative: Manual Command

If you prefer to run manually:

1. Open Command Prompt
2. Run these commands:
   ```cmd
   cd C:\ecommerceweb\backend
   mvn spring-boot:run
   ```

---

## ❓ Troubleshooting

### "This site can't be reached"
- Wait for the message: "Started ArtShopApplication"
- Make sure you see: "Tomcat started on port(s): 8080"
- Then refresh your browser

### "Port 8080 already in use"
- Another application is using port 8080
- Close it or change port in `application.properties`

### Application won't start
- Make sure you have Java 17 installed
- Make sure Maven is installed
- Check if `backend` folder exists

---

## 📝 Summary

**To Run:** Double-click `RUN.bat`

**To Access:** Open `http://localhost:8080`

**To Login:** `john@example.com` / `password123`

**To Stop:** Press `Ctrl + C`

---

## 🎉 Features

- 🎨 Browse art gallery with categories
- 🛒 Shopping cart
- 💳 Razorpay payment integration
- 👤 User authentication (Customer & Artist)
- 🖼️ Artist dashboard for uploading artworks
- 📦 Order management
- 📱 Responsive design
- 💾 Permanent data storage

---

## 📚 Additional Documentation

- `START_HERE.md` - Quick start guide
- `SIMPLE_RUN.md` - Simple running instructions
- `MONGODB_SETUP.md` - MongoDB configuration details
- `RAZORPAY_INTEGRATION.md` - Payment integration guide

---

**Enjoy your Art Shop! 🎨✨**

If you have any issues, check the command window for error messages.
