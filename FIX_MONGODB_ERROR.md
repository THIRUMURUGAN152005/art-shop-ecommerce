# 🔧 Fix MongoDB Connection Error

## ❌ Error You're Seeing:
```
connect ECONNREFUSED 127.0.0.1:27017
```

This means: **MongoDB is not running!**

---

## ✅ Solution: Start MongoDB First

### **Step 1: In MongoDB Compass**

1. **Click on "localhost:27017"** connection
2. **Click the green "CONNECT" button**
3. Wait for it to say "Connected"
4. **Keep MongoDB Compass open**

### **Step 2: Then Run Your Application**

Open Command Prompt and run:
```cmd
cd C:\ecommerceweb\backend
mvn spring-boot:run
```

---

## 🎯 Correct Order Every Time:

```
1. Start MongoDB Compass → Click "CONNECT" on localhost:27017
2. Wait for "Connected" status
3. Run: cd backend
4. Run: mvn spring-boot:run
5. Open: http://localhost:8080
```

---

## 🔍 How to Check if MongoDB is Running:

Open Command Prompt and run:
```cmd
netstat -ano | findstr :27017
```

If you see output, MongoDB is running ✅
If no output, MongoDB is NOT running ❌

---

## 🆘 Alternative: If MongoDB Compass Won't Connect

### Option A: Install MongoDB Community Server

1. Download: https://www.mongodb.com/try/download/community
2. Install (may need admin rights)
3. MongoDB will run as Windows service automatically
4. Then run your Spring Boot app

### Option B: Use Portable MongoDB

1. Download MongoDB ZIP (portable)
2. Extract to `C:\mongodb`
3. Create `start-mongodb.bat`:
   ```bat
   "C:\mongodb\bin\mongod.exe" --dbpath "C:\ecommerceweb\backend\mongodb-data"
   ```
4. Double-click `start-mongodb.bat` (keep window open)
5. Then run your Spring Boot app

---

## ✅ Once MongoDB is Running:

Your data will be **PERMANENT**:
- ✅ All users saved
- ✅ All artworks saved
- ✅ All orders saved
- ✅ Data persists across restarts

---

## 📝 Summary

**The Error Means:** MongoDB is not running

**The Fix:** Start MongoDB BEFORE running Spring Boot

**How:** Click "CONNECT" in MongoDB Compass on localhost:27017

---

**Need Help?** Make sure MongoDB Compass is installed and the localhost:27017 connection shows "Connected" status.
