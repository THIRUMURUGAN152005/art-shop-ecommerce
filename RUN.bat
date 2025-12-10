@echo off
title Art Shop - Starting...
color 0A

echo.
echo ========================================
echo    ART SHOP - STARTING APPLICATION
echo ========================================
echo.
echo Please wait while the application starts...
echo.

cd /d "%~dp0backend"

echo [INFO] Starting Spring Boot with Embedded MongoDB...
echo [INFO] This may take 20-30 seconds...
echo.

call mvn spring-boot:run

pause
