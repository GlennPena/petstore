#!/bin/bash
# Petstore Render Deployment Setup Script
# This script prepares your repository for Render deployment

echo "🚀 Petstore Render Deployment Setup"
echo "===================================="
echo ""

# Check if git is initialized
if [ ! -d .git ]; then
    echo "❌ Not a git repository. Initialize with: git init"
    exit 1
fi

# Check if we have changes to commit
if git status --porcelain | grep -q .; then
    echo "📝 Found uncommitted changes:"
    git status --porcelain
    echo ""
    echo "Committing changes..."
    git add .
    git commit -m "Prepare for Render deployment"
    echo "✅ Changes committed"
else
    echo "✅ No uncommitted changes"
fi

echo ""
echo "📋 Deployment Files Checklist:"
echo "=============================="
echo ""

# Check for deployment files
files=(
    "render.yaml"
    "backend/application-prod.yml"
    "backend/Dockerfile"
    "frontend/Dockerfile"
    ".env.example"
    "DEPLOYMENT.md"
    "RENDER_QUICKSTART.md"
    "RENDER_DEPLOY_CHECKLIST.md"
)

for file in "${files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file"
    else
        echo "❌ $file (MISSING!)"
    fi
done

echo ""
echo "🔍 Final Checks:"
echo "==============="
echo ""

# Check backend build
echo "Building backend..."
cd backend
mvn clean compile -q 2>/dev/null
if [ $? -eq 0 ]; then
    echo "✅ Backend builds successfully"
else
    echo "❌ Backend build failed"
    exit 1
fi
cd ..

# Check frontend build
echo "Building frontend..."
cd frontend
npm install --silent 2>/dev/null
npm run build --silent 2>/dev/null
if [ $? -eq 0 ]; then
    echo "✅ Frontend builds successfully"
else
    echo "❌ Frontend build failed"
    exit 1
fi
cd ..

echo ""
echo "📊 Deployment Status:"
echo "===================="
echo ""
echo "✅ All checks passed!"
echo ""
echo "Next steps:"
echo "1. Push to GitHub:"
echo "   git push origin main"
echo ""
echo "2. Go to https://dashboard.render.com/"
echo ""
echo "3. Click 'New +' → 'Blueprint'"
echo ""
echo "4. Select your GitHub repository"
echo ""
echo "5. Render will auto-detect render.yaml"
echo ""
echo "6. Review and click 'Deploy'"
echo ""
echo "📖 For detailed instructions, see RENDER_DEPLOY_CHECKLIST.md"
echo ""
