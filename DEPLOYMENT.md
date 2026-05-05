# Petstore Deployment Guide - Render.com

## Prerequisites
- Render.com account
- GitHub repository with the petstore code
- PostgreSQL database on Render (or external)

## Step-by-Step Deployment

### 1. Push Code to GitHub
```bash
git add .
git commit -m "Prepare for Render deployment"
git push origin main
```

### 2. Create PostgreSQL Database on Render
1. Go to https://dashboard.render.com/
2. Click "New +" → "PostgreSQL"
3. Enter database name: `petstore-db`
4. Select region closest to you
5. Select plan (free or paid)
6. Click "Create Database"
7. Copy the **Internal Database URL** (will be used in services)

### 3. Deploy Services via render.yaml

#### Option A: Using Web Service (Recommended)

**Backend Service:**
1. Click "New +" → "Web Service"
2. Select your GitHub repository
3. Configure:
   - Name: `petstore-backend`
   - Environment: `Docker`
   - Root Directory: `backend`
   - Build Command: (leave auto-detect or use custom)
   - Start Command: 
     ```
     java -Dspring.profiles.active=prod \
       -Dserver.port=$PORT \
       -jar target/petstore-backend-0.0.1-SNAPSHOT.jar
     ```
4. Add Environment Variables:
   - `DATABASE_URL`: Paste PostgreSQL connection string from step 2
   - `CORS_ALLOWED_ORIGINS`: `https://petstore-frontend.onrender.com`
   - `SPRING_PROFILES_ACTIVE`: `prod`
5. Click "Create Web Service"

**Frontend Service:**
1. Click "New +" → "Web Service"
2. Select your GitHub repository
3. Configure:
   - Name: `petstore-frontend`
   - Environment: `Docker`
   - Root Directory: `frontend`
   - Build Command: (leave auto-detect)
   - Start Command: `npm run preview`
4. Add Environment Variables:
   - `VITE_API_BASE_URL`: Paste the backend service URL (e.g., `https://petstore-backend.onrender.com`)
5. Click "Create Web Service"

### 4. Verify Environment Variables

After services are created, verify variables are set:

**Backend:**
- `DATABASE_URL`: Your PostgreSQL connection string
- `CORS_ALLOWED_ORIGINS`: Frontend URL
- `SPRING_PROFILES_ACTIVE`: `prod`

**Frontend:**
- `VITE_API_BASE_URL`: Backend service URL

### 5. Monitor Deployment

1. Check service logs in Render dashboard
2. Verify backend is running: `https://petstore-backend.onrender.com/actuator/health`
3. Verify frontend is accessible: `https://petstore-frontend.onrender.com`
4. Test API call: `https://petstore-backend.onrender.com/pena/pets`

## Troubleshooting

### Backend Service Won't Start
- Check logs: Look for Java/Spring errors
- Verify `DATABASE_URL` format is correct
- Ensure database is accessible from Render
- Check that `application-prod.yml` exists

### Frontend Won't Load
- Verify `VITE_API_BASE_URL` is correct
- Check browser console for CORS errors
- Verify backend is accessible from frontend

### Database Connection Issues
- Verify `DATABASE_URL` in environment variables
- Check PostgreSQL database is running
- Ensure IP allowlist permits Render services
- Test connection: `psql $DATABASE_URL`

### CORS Errors
- Verify `CORS_ALLOWED_ORIGINS` includes frontend URL
- Check backend logs for CORS configuration
- Ensure no trailing slashes in URLs

## Important Notes

1. **Free Plan Limitations:**
   - Services spin down after 15 minutes of inactivity
   - First request after spin-down may be slow
   - Limited to 512MB RAM per service
   - PostgreSQL free tier has 90-day deletion policy

2. **Production Recommendations:**
   - Use paid plans for production
   - Set up automatic backups for database
   - Enable SSL/TLS (automatic on Render)
   - Monitor service performance
   - Set up error tracking (e.g., Sentry)

3. **Data Seeding:**
   - Database initializes with 6 pets on first startup
   - Data persists across restarts
   - To reset, run migration or update database directly

## Database Credentials

The backend connects using the `DATABASE_URL` environment variable format:
```
postgresql://username:password@host:port/database
```

Render automatically provides this when you link services.

## Local Development

To test locally before deploying:

1. **Start PostgreSQL:**
   ```bash
   docker run --name petstore-db \
     -e POSTGRES_DB=petstore \
     -e POSTGRES_PASSWORD=petstore \
     -p 5432:5432 \
     postgres:15
   ```

2. **Backend:**
   ```bash
   cd backend
   export SPRING_PROFILES_ACTIVE=prod
   export DATABASE_URL=postgresql://postgres:petstore@localhost:5432/petstore
   mvn spring-boot:run
   ```

3. **Frontend:**
   ```bash
   cd frontend
   export VITE_API_BASE_URL=http://localhost:8080
   npm run dev
   ```

## Support

For issues with:
- **Render deployment:** Check Render documentation at https://render.com/docs
- **Spring Boot:** See https://spring.io/projects/spring-boot
- **React/Vite:** See https://vitejs.dev/guide/
