@echo off
REM Petstore Render Deployment Setup Script (Windows)
REM This script prepares your repository for Render deployment

echo.
echo 🚀 Petstore Render Deployment Setup
echo ====================================
echo.

REM Check if git is initialized
if not exist .git (
    echo ❌ Not a git repository. Initialize with: git init
    exit /b 1
)

REM Check if we have changes to commit
git status --porcelain >nul 2>&1
if %errorlevel% equ 0 (
    for /f %%i in ('git status --porcelain ^| find /c ""') do (
        if %%i gtr 0 (
            echo 📝 Found uncommitted changes:
            git status --porcelain
            echo.
            echo Committing changes...
            git add .
            git commit -m "Prepare for Render deployment"
            echo ✅ Changes committed
        ) else (
            echo ✅ No uncommitted changes
        )
    )
)

echo.
echo 📋 Deployment Files Checklist:
echo ==============================
echo.

REM Check for deployment files
setlocal enabledelayedexpansion
set files=render.yaml backend\application-prod.yml backend\Dockerfile frontend\Dockerfile .env.example DEPLOYMENT.md RENDER_QUICKSTART.md RENDER_DEPLOY_CHECKLIST.md

for %%f in (%files%) do (
    if exist %%f (
        echo ✅ %%f
    ) else (
        echo ❌ %%f (MISSING^!)
    )
)

echo.
echo 🔍 Final Checks:
echo ===============
echo.

REM Check backend build
echo Building backend...
cd backend
call mvn clean compile -q 2>nul
if %errorlevel% equ 0 (
    echo ✅ Backend builds successfully
) else (
    echo ❌ Backend build failed
    cd ..
    exit /b 1
)
cd ..

REM Check frontend build
echo Building frontend...
cd frontend
call npm install --silent 2>nul
call npm run build --silent 2>nul
if %errorlevel% equ 0 (
    echo ✅ Frontend builds successfully
) else (
    echo ❌ Frontend build failed
    cd ..
    exit /b 1
)
cd ..

echo.
echo 📊 Deployment Status:
echo ====================
echo.
echo ✅ All checks passed!
echo.
echo Next steps:
echo 1. Push to GitHub:
echo    git push origin main
echo.
echo 2. Go to https://dashboard.render.com/
echo.
echo 3. Click 'New +' → 'Blueprint'
echo.
echo 4. Select your GitHub repository
echo.
echo 5. Render will auto-detect render.yaml
echo.
echo 6. Review and click 'Deploy'
echo.
echo 📖 For detailed instructions, see RENDER_DEPLOY_CHECKLIST.md
echo.
pause
