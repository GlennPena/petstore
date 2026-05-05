# Petstore Render Deployment Checklist

## Pre-Deployment Setup

- [ ] Render account created at https://render.com
- [ ] GitHub account with petstore repository
- [ ] Git credentials configured locally
- [ ] All code committed to GitHub main branch

## Step 1: Create PostgreSQL Database

- [ ] Go to https://dashboard.render.com/
- [ ] Click "New +" → "PostgreSQL"
- [ ] Fill in:
  - [ ] Name: `petstore-db`
  - [ ] Database: `petstore`
  - [ ] User: `petstore`
  - [ ] Select region (closest to you)
  - [ ] Select plan (Free or Paid)
- [ ] Click "Create Database"
- [ ] **Copy the Internal Database URL** (format: `postgresql://user:pass@host:port/db`)
- [ ] Wait for database to be ready (check status)

## Step 2: Deploy Backend Service

- [ ] Go to Render Dashboard
- [ ] Click "New +" → "Web Service"
- [ ] Connect GitHub repository
  - [ ] Search for your petstore repo
  - [ ] Click "Connect"
- [ ] Fill in settings:
  - [ ] Service Name: `petstore-backend`
  - [ ] Environment: `Docker`
  - [ ] Root Directory: `backend`
  - [ ] Select region (same as database)
  - [ ] Select plan (Free or Paid)
- [ ] **Add Environment Variables:**
  - [ ] Key: `DATABASE_URL` → Value: (paste from Step 1)
  - [ ] Key: `CORS_ALLOWED_ORIGINS` → Value: `http://localhost:5173` (will update after frontend deployed)
  - [ ] Key: `SPRING_PROFILES_ACTIVE` → Value: `prod`
- [ ] Click "Create Web Service"
- [ ] **Wait for deployment** (check logs)
- [ ] **Note the backend URL** when deployment completes (e.g., `https://petstore-backend.onrender.com`)

## Step 3: Deploy Frontend Service

- [ ] Go to Render Dashboard
- [ ] Click "New +" → "Web Service"
- [ ] Connect same GitHub repository
- [ ] Fill in settings:
  - [ ] Service Name: `petstore-frontend`
  - [ ] Environment: `Docker`
  - [ ] Root Directory: `frontend`
  - [ ] Select region (same as backend)
  - [ ] Select plan (Free or Paid)
- [ ] **Add Environment Variables:**
  - [ ] Key: `VITE_API_BASE_URL` → Value: (backend URL from Step 2)
- [ ] Click "Create Web Service"
- [ ] **Wait for deployment** (check logs)
- [ ] **Note the frontend URL** when deployment completes (e.g., `https://petstore-frontend.onrender.com`)

## Step 4: Update Backend CORS

Now that frontend is deployed, update backend CORS:

- [ ] Go to Backend Service in Render Dashboard
- [ ] Click "Environment"
- [ ] Find `CORS_ALLOWED_ORIGINS`
- [ ] Update value: Replace with frontend URL (e.g., `https://petstore-frontend.onrender.com`)
- [ ] Click "Save"
- [ ] Backend will auto-redeploy

## Step 5: Verify Deployment

- [ ] Test Backend Health:
  ```
  Open: https://petstore-backend.onrender.com/actuator/health
  Expected: {"status":"UP"}
  ```

- [ ] Test Backend API:
  ```
  Open: https://petstore-backend.onrender.com/pena/pets
  Expected: Array of 6 pets
  ```

- [ ] Test Frontend:
  ```
  Open: https://petstore-frontend.onrender.com
  Expected: Petstore catalog with 6 pets visible
  ```

- [ ] Test Full Flow:
  - [ ] Browse catalog
  - [ ] Add item to cart
  - [ ] View cart
  - [ ] Add item to wishlist
  - [ ] View wishlist

## Troubleshooting

### Backend Won't Start
- [ ] Check backend logs in Render dashboard
- [ ] Verify `DATABASE_URL` is correct and database is running
- [ ] Verify `CORS_ALLOWED_ORIGINS` format is correct
- [ ] Restart service from dashboard

### Frontend Shows Blank Page
- [ ] Check browser console (F12) for errors
- [ ] Verify `VITE_API_BASE_URL` is correct backend URL
- [ ] Check network tab for failed API requests
- [ ] Verify backend is accessible from frontend
- [ ] Restart service from dashboard

### CORS Errors
- [ ] Check browser console for exact error
- [ ] Verify `CORS_ALLOWED_ORIGINS` includes full frontend URL (with https://)
- [ ] Restart backend service
- [ ] Wait 1-2 minutes for service to fully restart

### Database Connection Errors
- [ ] Verify `DATABASE_URL` matches PostgreSQL connection string
- [ ] Check PostgreSQL database is running (status in Render)
- [ ] Verify username/password in connection string
- [ ] Test connection string locally if possible

### Services Won't Deploy
- [ ] Check deployment logs in Render dashboard
- [ ] Verify GitHub branch is main (or configured branch)
- [ ] Check if builds are too large (>512MB free limit)
- [ ] Try rebuilding from Render dashboard

## Optional: Setup GitHub Actions

For automatic deployment on push to main:

- [ ] Go to your GitHub repository
- [ ] Create `.github/workflows/ci-cd.yml` (file already in project)
- [ ] Add GitHub Secrets:
  - [ ] `RENDER_API_KEY`: Get from Render Account Settings
  - [ ] `RENDER_BACKEND_SERVICE_ID`: From backend service URL
  - [ ] `RENDER_FRONTEND_SERVICE_ID`: From frontend service URL
- [ ] Push to main branch
- [ ] Check GitHub Actions tab for deployment status

## Free Tier Notes ⚠️

- **Services spin down** after 15 minutes of inactivity
- **First request** after spin-down may take 30+ seconds
- **Limited resources**: 512MB RAM per service
- **Database**: 90-day deletion policy (paid tier recommended)

## Upgrade to Production

When ready for production:

1. Upgrade each service plan (Backend, Frontend, Database)
2. Keep same configuration (will scale automatically)
3. Enable automatic backups for database
4. Set up monitoring and alerts
5. Configure custom domain (optional)

## Support

- **Render Docs**: https://render.com/docs
- **Backend Issues**: Check `application-prod.yml` configuration
- **Frontend Issues**: Check `vite.config.js` and environment variables
- **Database Issues**: Check PostgreSQL connection string format

---

✅ **Deployment Complete!** Your Petstore is now live!
