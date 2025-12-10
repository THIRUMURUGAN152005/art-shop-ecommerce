# 🚀 Art Shop - Run Commands

## Every Time You Want to Run the Project:

### Step 1: Start MongoDB (Terminal 1)

**Open Command Prompt as Administrator** and run:

```cmd
net start MongoDB
```

**OR** if MongoDB is not installed as a service:

```cmd
"C:\Program Files\MongoDB\Server\7.0\bin\mongod.exe" --dbpath "C:\ecommerceweb\backend\mongodb-data"
```

(Replace `7.0` with your MongoDB version if different)

**Keep this terminal open!**

---

### Step 2: Start Spring Boot (Terminal 2)

**Open a new Command Prompt** (regular, not admin) and run:

```cmd
cd C:\ecommerceweb\backend
mvn spring-boot:run
```

**Wait for the message**: `Started ArtShopApplication`

---

### Step 3: Open in Browser

```
http://localhost:8080
```

---

## 🔐 Login Credentials

**Customer:**
- Email: `john@example.com`
- Password: `password123`

**Artist:**
- Email: `vincent@artshop.com`
- Password: `password123`

---

## 💳 Test Razorpay Payment

**Test Card:**
- Card: `4111 1111 1111 1111`
- CVV: `123`
- Expiry: `12/25`

---

## 🛑 To Stop the Application

1. **Stop Spring Boot**: Press `Ctrl + C` in Terminal 2
2. **Stop MongoDB**: 
   - If service: `net stop MongoDB` (as admin)
   - If manual: Press `Ctrl + C` in Terminal 1

---

## ⚡ Quick Reference

| Action | Command |
|--------|---------|
| Start MongoDB | `net start MongoDB` |
| Stop MongoDB | `net stop MongoDB` |
| Start App | `cd backend` then `mvn spring-boot:run` |
| Open App | `http://localhost:8080` |
| Check MongoDB | `netstat -ano \| findstr :27017` |
| Check App | `netstat -ano \| findstr :8080` |

---

## 📝 Notes

- MongoDB must be running BEFORE starting Spring Boot
- All data is saved permanently in `backend/mongodb-data/`
- Keep both terminals open while using the app
- Your data persists across restarts ✅

---

**That's it! Just 2 commands every time! 🎉**
