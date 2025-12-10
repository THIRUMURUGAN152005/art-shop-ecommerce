# MongoDB Permanent Storage Setup

## Overview
This guide explains how to set up MongoDB with permanent storage for the Art Shop application.

## Current Setup
- **Database Name**: `artshop`
- **Data Directory**: `backend/mongodb-data/`
- **Port**: `27017`
- **Connection String**: `mongodb://localhost:27017/artshop`

## Storage Location
All your data is permanently stored in:
```
C:\ecommerceweb\backend\mongodb-data\
```

This folder contains:
- User accounts
- Artworks
- Orders
- All application data

**Important**: This data persists across application restarts!

## Starting MongoDB

### Option 1: Using the Startup Script (Easiest)

1. **Start MongoDB** (in one terminal):
   ```cmd
   start-mongodb.bat
   ```
   Keep this window open while using the application.

2. **Start Art Shop** (in another terminal):
   ```cmd
   start-artshop.bat
   ```

### Option 2: Manual Start

1. **Open Command Prompt**

2. **Start MongoDB with data directory**:
   ```cmd
   mongod --dbpath "C:\ecommerceweb\backend\mongodb-data"
   ```

3. **In another terminal, start Spring Boot**:
   ```cmd
   cd backend
   mvn spring-boot:run
   ```

### Option 3: Install MongoDB as Windows Service (Recommended for Production)

1. **Open Command Prompt as Administrator**

2. **Install MongoDB Service**:
   ```cmd
   mongod --dbpath "C:\ecommerceweb\backend\mongodb-data" --install --serviceName "ArtShopMongoDB"
   ```

3. **Start the service**:
   ```cmd
   net start ArtShopMongoDB
   ```

4. **Now MongoDB will start automatically with Windows!**

To stop the service:
```cmd
net stop ArtShopMongoDB
```

To remove the service:
```cmd
mongod --remove --serviceName "ArtShopMongoDB"
```

## Verifying MongoDB is Running

### Check if MongoDB is running:
```cmd
netstat -ano | findstr :27017
```

If you see output, MongoDB is running.

### Connect to MongoDB Shell:
```cmd
mongosh
```

Then check your database:
```javascript
use artshop
show collections
db.users.find()
db.artworks.find()
db.orders.find()
```

## Database Initialization

On first run, the application will automatically create:
- **4 sample users** (2 customers, 2 artists)
- **6 sample artworks**

### Sample Login Credentials:
- **Customer**: `john@example.com` / `password123`
- **Artist**: `vincent@artshop.com` / `password123`

## Data Persistence

### What Gets Saved Permanently:
✅ User registrations
✅ Artworks uploaded by artists
✅ Orders and payments
✅ Shopping cart data (in browser localStorage)
✅ All database changes

### Data Location:
All data is stored in: `backend/mongodb-data/`

**Backup this folder to backup your entire database!**

## Backup and Restore

### Backup Database:
```cmd
mongodump --db artshop --out "C:\backup\artshop-backup"
```

### Restore Database:
```cmd
mongorestore --db artshop "C:\backup\artshop-backup\artshop"
```

### Simple Backup (Copy folder):
Just copy the entire `backend/mongodb-data/` folder to a safe location.

## Troubleshooting

### MongoDB Won't Start
**Error**: "Data directory not found"
**Solution**: 
```cmd
mkdir backend\mongodb-data
```

**Error**: "Port 27017 already in use"
**Solution**: Another MongoDB instance is running. Stop it first:
```cmd
taskkill /F /IM mongod.exe
```

### Application Can't Connect to MongoDB
1. Verify MongoDB is running:
   ```cmd
   netstat -ano | findstr :27017
   ```

2. Check connection string in `application.properties`:
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/artshop
   ```

3. Try connecting with MongoDB shell:
   ```cmd
   mongosh mongodb://localhost:27017/artshop
   ```

### Data Not Persisting
- Make sure you're NOT using dev profile (embedded MongoDB)
- Check `application.properties`:
  ```properties
  # This should be commented out:
  # spring.profiles.active=dev
  ```

### Clear All Data (Reset Database)
If you want to start fresh:

1. Stop MongoDB
2. Delete contents of `backend/mongodb-data/` folder
3. Restart MongoDB
4. Restart Spring Boot (will reinitialize with sample data)

## Production Recommendations

For production deployment:

1. **Use MongoDB Atlas** (cloud-hosted):
   - Free tier available
   - Automatic backups
   - High availability
   - Update connection string:
     ```properties
     spring.data.mongodb.uri=mongodb+srv://username:password@cluster.mongodb.net/artshop
     ```

2. **Or use local MongoDB with**:
   - Regular automated backups
   - Monitoring
   - Security (authentication enabled)
   - Replica sets for high availability

## Security Notes

Current setup is for **development only**:
- ⚠️ No authentication required
- ⚠️ Accessible from localhost only
- ⚠️ No encryption

For production, enable authentication:
```cmd
mongod --dbpath "C:\ecommerceweb\backend\mongodb-data" --auth
```

Then create admin user and update connection string.

## Monitoring Database Size

Check database size:
```javascript
// In mongosh
use artshop
db.stats()
```

Check collection sizes:
```javascript
db.users.stats()
db.artworks.stats()
db.orders.stats()
```

## Performance Tips

1. **Create indexes** for frequently queried fields
2. **Regular backups** - schedule daily backups
3. **Monitor disk space** - MongoDB data grows over time
4. **Clean old data** - archive old orders periodically

## Support

For MongoDB issues:
- MongoDB Documentation: https://docs.mongodb.com/
- MongoDB Community: https://community.mongodb.com/

For Art Shop issues:
- Check application logs
- Review `backend/logs/` folder
- Contact: support@artshop.com

---

**Last Updated**: November 28, 2025
**MongoDB Version**: 4.4+
**Storage Type**: Permanent (WiredTiger)
