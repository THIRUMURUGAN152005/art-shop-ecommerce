# 🎨 Art Shop - Permanent Storage Solution

## ⚠️ Current Situation

Embedded MongoDB (in-memory) resets data on every restart.

For **PERMANENT storage**, you have 2 options:

---

## ✅ Option 1: Use MongoDB Compass (EASIEST - Recommended)

MongoDB Compass is a free GUI tool that includes a local MongoDB server.

### Steps:

1. **Download MongoDB Compass** (Free):
   - Go to: https://www.mongodb.com/try/download/compass
   - Download and install (no admin rights needed for user install)

2. **Start MongoDB Compass**
   - It will automatically start a local MongoDB server
   - Connect to: `mongodb://localhost:27017`

3. **Run Your Application**:
   ```cmd
   cd C:\ecommerceweb\backend
   mvn spring-boot:run
   ```

4. **Your data is now permanent!**
   - Stored in MongoDB Compass's data directory
   - Persists across restarts

---

## ✅ Option 2: Use MongoDB Community Server

### Steps:

1. **Download MongoDB Community Server**:
   - Go to: https://www.mongodb.com/try/download/community
   - Download the MSI installer
   - Install (may need admin rights)

2. **MongoDB will install as a Windows Service**
   - Starts automatically with Windows
   - No need to start manually

3. **Run Your Application**:
   ```cmd
   cd C:\ecommerceweb\backend
   mvn spring-boot:run
   ```

4. **Your data is permanent!**
   - Stored in: `C:\Program Files\MongoDB\Server\<version>\data`
   - Persists across restarts

---

## ✅ Option 3: Portable MongoDB (No Installation)

### Steps:

1. **Download MongoDB ZIP** (portable version):
   - Go to: https://www.mongodb.com/try/download/community
   - Select "ZIP" instead of "MSI"
   - Extract to any folder (e.g., `C:\mongodb`)

2. **Create a batch file** `start-mongodb-portable.bat`:
   ```bat
   @echo off
   "C:\mongodb\bin\mongod.exe" --dbpath "C:\ecommerceweb\backend\mongodb-data"
   ```

3. **Run MongoDB** (double-click the batch file)
   - Keep the window open

4. **In another terminal, run your app**:
   ```cmd
   cd C:\ecommerceweb\backend
   mvn spring-boot:run
   ```

5. **Your data is permanent!**
   - Stored in: `C:\ecommerceweb\backend\mongodb-data\`
   - Persists across restarts

---

## 🎯 Recommended: Option 1 (MongoDB Compass)

**Why?**
- ✅ Easiest to install
- ✅ No admin rights needed
- ✅ Includes GUI to view your data
- ✅ Automatic startup
- ✅ Permanent storage
- ✅ Free

**Download**: https://www.mongodb.com/try/download/compass

---

## 📝 After Installing MongoDB

### Your workflow will be:

1. **Start MongoDB** (Compass or Service - usually automatic)
2. **Run Application**:
   ```cmd
   cd C:\ecommerceweb\backend
   mvn spring-boot:run
   ```
3. **Open Browser**: `http://localhost:8080`

### All your data will be permanent! ✅

---

## 🔧 Current Configuration

Your app is configured to connect to:
```
mongodb://localhost:27017/artshop
```

This works with:
- ✅ MongoDB Compass
- ✅ MongoDB Community Server
- ✅ Portable MongoDB

---

## 💡 Quick Test

After installing MongoDB (any option above):

1. Check if MongoDB is running:
   ```cmd
   netstat -ano | findstr :27017
   ```
   
2. If you see output, MongoDB is running!

3. Run your app:
   ```cmd
   cd backend
   mvn spring-boot:run
   ```

4. Create some data (users, artworks, orders)

5. Stop and restart the app

6. **Your data will still be there!** ✅

---

## 🎉 Summary

**For Permanent Storage:**
1. Install MongoDB Compass (easiest)
2. Run your Spring Boot app
3. All data persists forever!

**No more data loss on restart!** 🎨✨

---

**Download MongoDB Compass Now**: https://www.mongodb.com/try/download/compass
