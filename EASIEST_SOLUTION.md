# 🎯 EASIEST SOLUTION - Permanent MongoDB

## Problem
MongoDB Compass is not connecting properly.

## ✅ SOLUTION: Use Batch Files (No Compass Needed!)

### **Step 1: Download & Install MongoDB Community Server**

**Download:** https://www.mongodb.com/try/download/community

1. Click "Download" (MSI installer)
2. Run the installer
3. Choose "Complete" installation
4. **Uncheck** "Install MongoDB Compass" (we don't need it)
5. Finish installation

### **Step 2: Run Your Art Shop**

#### **Terminal 1: Start MongoDB**
Double-click: **`START-MONGODB-PERMANENT.bat`**

Keep this window open!

#### **Terminal 2: Start Spring Boot**
Double-click: **`RUN.bat`**

OR manually:
```cmd
cd C:\ecommerceweb\backend
mvn spring-boot:run
```

#### **Browser:**
Open: **http://localhost:8080**

---

## 🎉 That's It!

### **Every Time You Want to Run:**

1. **Double-click:** `START-MONGODB-PERMANENT.bat` (keep open)
2. **Double-click:** `RUN.bat`
3. **Open:** http://localhost:8080

### **Your Data is PERMANENT!**
- ✅ Stored in: `backend/mongodb-data/`
- ✅ Never deleted
- ✅ Persists across restarts

---

## 🔧 Alternative: If MongoDB is Already Installed

If MongoDB is already installed but not working:

### **Option A: Start as Windows Service**

Open Command Prompt as Administrator:
```cmd
net start MongoDB
```

Then run:
```cmd
cd C:\ecommerceweb\backend
mvn spring-boot:run
```

### **Option B: Find mongod.exe Manually**

1. Search for `mongod.exe` on your computer
2. Note the path (e.g., `C:\Program Files\MongoDB\Server\7.0\bin\mongod.exe`)
3. Edit `START-MONGODB-PERMANENT.bat` and update the path
4. Run the batch file

---

## 📝 Summary

**Simplest Way:**
1. Install MongoDB Community Server (one-time)
2. Double-click `START-MONGODB-PERMANENT.bat`
3. Double-click `RUN.bat`
4. Open http://localhost:8080

**All data is permanent!** ✅

---

## 🆘 Still Having Issues?

If MongoDB won't install or start, you have one more option:

### **Use Cloud MongoDB (MongoDB Atlas) - FREE**

1. Go to: https://www.mongodb.com/cloud/atlas/register
2. Create free account
3. Create free cluster (M0 - Free tier)
4. Get connection string
5. Update `application.properties`:
   ```properties
   spring.data.mongodb.uri=mongodb+srv://username:password@cluster.mongodb.net/artshop
   ```

This gives you permanent cloud storage with zero local setup!

---

**Download MongoDB:** https://www.mongodb.com/try/download/community

**Then run:** `START-MONGODB-PERMANENT.bat` + `RUN.bat`
