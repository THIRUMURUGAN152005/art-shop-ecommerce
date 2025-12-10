# 🚀 Art Shop - SIMPLE RUN (No Admin Rights Needed!)

## ✅ ONE COMMAND TO RUN EVERYTHING!

Your project is now configured to use **embedded MongoDB with permanent storage**.

This means:
- ✅ No need to start MongoDB separately
- ✅ No admin rights required
- ✅ Data is PERMANENT (saved in `backend/mongodb-data/`)
- ✅ Everything starts with one command!

---

## 🎯 EVERY TIME - Just Run This:

### Open Command Prompt (regular, not admin) and run:

```cmd
cd C:\ecommerceweb\backend
mvn spring-boot:run
```

**That's it!** 

Wait for: `Started ArtShopApplication`

Then open browser: **http://localhost:8080**

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

## 📊 Your Data is Permanent!

Everything is saved in: `backend/mongodb-data/`

This includes:
- ✅ Users
- ✅ Artworks
- ✅ Orders
- ✅ All changes

**Data persists across restarts!**

---

## 🛑 To Stop

Press `Ctrl + C` in the terminal

---

## 🎉 Summary

**Start:** `cd backend` → `mvn spring-boot:run`

**Open:** `http://localhost:8080`

**Login:** `john@example.com` / `password123`

**That's all!** No MongoDB service, no admin rights needed! 🚀

---

## 📝 Technical Details

- Embedded MongoDB starts automatically with Spring Boot
- Data directory: `backend/mongodb-data/`
- Port: 27017 (MongoDB), 8080 (Application)
- Profile: `dev` (with permanent storage)

---

**Enjoy your Art Shop! 🎨✨**
