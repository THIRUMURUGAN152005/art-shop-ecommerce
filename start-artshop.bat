@echo off
echo ========================================
echo    Art Shop - Startup Script
echo ========================================
echo.

REM Check if MongoDB is running
echo [1/3] Checking MongoDB connection...
timeout /t 2 /nobreak >nul

REM Navigate to backend directory
cd backend

echo [2/3] Starting Spring Boot Application...
echo.
call mvn spring-boot:run

pause
