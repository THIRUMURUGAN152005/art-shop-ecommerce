# 🎨 Art Shop - Quick Start Guide

## ✅ Your Database is Already Set Up!

Good news! You already have a permanent MongoDB database with data in:
```
C:\ecommerceweb\backend\mongodb-data\
```

This folder contains all your permanent data (users, artworks, orders).

## 🚀 How to Start the Application

### Step 1: Start MongoDB

You need to start MongoDB first. Choose ONE of these methods:

#### Method A: If MongoDB is Installed as a Service (Easiest)
Open **Command Prompt as Administrator** and run:
```cmd
net start MongoDB
```

#### Method B: If MongoDB is Not a Service
1. Open **Command Prompt**
2. Navigate to your MongoDB installation (usually):
   ```cmd
   cd "C:\Program Files\MongoDB\Server\7.0\bin"
   ```
   (Replace `7.0` with your MongoDB version)

3. Start MongoDB with your data directory:
   ```cmd
   mongod --dbpath "C:\ecommerceweb\backend\mongodb-data"
   ```
   
   **Keep this window open!**

#### Method C: Using the Batch File
Double-click: `start-mongodb.bat`

(Note: This requires MongoDB to be in your system PATH)

### Step 2: Start Spring Boot Application

Once MongoDB is running, in a **new terminal**:

```cmd
cd C:\ecommerceweb\backend
mvn spring-boot:run
```

Or double-click: `start-artshop.bat`

### Step 3: Open in Browser

Navigate to: **http://localhost:8080**

## 🔐 Login Credentials

### Customer Account:
- Email: `john@example.com`
- Password: `password123`

### Artist Account:
- Email: `vincent@artshop.com`
- Password: `password123`

## 💳 Test Razorpay Payment

1. Login as customer
2. Add artworks to cart
3. Go to cart page
4. Fill delivery details
5. Click "Pay with Razorpay"
6. Use test card:
   - **Card Number**: `4111 1111 1111 1111`
   - **CVV**: `123`
   - **Expiry**: `12/25` (any future date)

## 📊 Your Data is Permanent!

Everything you create will be saved permanently in:
```
backend/mongodb-data/
```

This includes:
- ✅ New user registrations
- ✅ Artworks uploaded by artists
- ✅ Orders and payments
- ✅ All changes to the database

**Backup this folder to backup your entire database!**

## 🔧 Troubleshooting

### "MongoDB connection refused"
- Make sure MongoDB is running (Step 1)
- Check if port 27017 is in use:
  ```cmd
  netstat -ano | findstr :27017
  ```

### "Port 8080 already in use"
- Another application is using port 8080
- Stop it or change the port in `application.properties`

### MongoDB won't start
- Check if MongoDB is installed
- Verify the path to mongod.exe
- Make sure `backend/mongodb-data` folder exists

## 📁 Project Structure

```
ecommerceweb/
├── backend/
│   ├── mongodb-data/          ← Your permanent database
│   ├── src/
│   └── pom.xml
├── start-mongodb.bat          ← Start MongoDB
├── start-artshop.bat          ← Start Spring Boot
├── MONGODB_SETUP.md           ← Detailed MongoDB guide
└── RAZORPAY_INTEGRATION.md    ← Payment integration guide
```

## 🎯 Features

- 🎨 Art Gallery with categories
- 🛒 Shopping Cart
- 💳 Razorpay Payment Integration
- 👤 User Authentication (Customer & Artist)
- 🖼️ Artist Dashboard for uploading artworks
- 📦 Order Management
- 📱 Responsive Design

## 📚 Documentation

- **MongoDB Setup**: See `MONGODB_SETUP.md`
- **Razorpay Integration**: See `RAZORPAY_INTEGRATION.md`
- **API Documentation**: See `backend/README.md`

## 🆘 Need Help?

1. Check the documentation files
2. Review browser console (F12) for errors
3. Check Spring Boot logs in terminal
4. Verify MongoDB is running

## 🎉 You're All Set!

Your Art Shop is ready to use with permanent storage. All data will persist across restarts!

---

**Quick Commands:**
```cmd
# Start MongoDB (as admin)
net start MongoDB

# Start Application
cd backend
mvn spring-boot:run

# Access Application
http://localhost:8080
```

Happy Selling! 🎨✨
