@echo off
title MongoDB Server - Permanent Storage
color 0A

echo ========================================
echo   MONGODB - PERMANENT STORAGE
echo ========================================
echo.
echo Starting MongoDB with permanent data storage...
echo Data location: %cd%\backend\mongodb-data
echo.
echo IMPORTANT: Keep this window open!
echo.

REM Try common MongoDB installation paths
set MONGO_PATH=

if exist "C:\Program Files\MongoDB\Server\7.0\bin\mongod.exe" (
    set MONGO_PATH=C:\Program Files\MongoDB\Server\7.0\bin\mongod.exe
) else if exist "C:\Program Files\MongoDB\Server\6.0\bin\mongod.exe" (
    set MONGO_PATH=C:\Program Files\MongoDB\Server\6.0\bin\mongod.exe
) else if exist "C:\Program Files\MongoDB\Server\5.0\bin\mongod.exe" (
    set MONGO_PATH=C:\Program Files\MongoDB\Server\5.0\bin\mongod.exe
) else if exist "C:\Program Files\MongoDB\Server\4.4\bin\mongod.exe" (
    set MONGO_PATH=C:\Program Files\MongoDB\Server\4.4\bin\mongod.exe
) else (
    echo ERROR: MongoDB not found!
    echo.
    echo Please install MongoDB from:
    echo https://www.mongodb.com/try/download/community
    echo.
    pause
    exit
)

echo Found MongoDB at: %MONGO_PATH%
echo.

"%MONGO_PATH%" --dbpath "%cd%\backend\mongodb-data" --port 27017

pause
