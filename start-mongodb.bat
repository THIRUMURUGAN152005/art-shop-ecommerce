@echo off
echo Starting MongoDB with permanent storage...
echo Data will be stored in: %cd%\backend\mongodb-data
echo.

REM Start MongoDB with the local data directory
mongod --dbpath "%cd%\backend\mongodb-data" --port 27017

pause
