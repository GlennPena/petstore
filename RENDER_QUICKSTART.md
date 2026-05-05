# Petstore - Quick Render Deployment

## Pre-Deployment Checklist

- [ ] Code pushed to GitHub
- [ ] GitHub token/SSH key configured
- [ ] Render.com account created
- [ ] PostgreSQL database created on Render
- [ ] Environment variables documented

## One-Command Deployment (if using render.yaml)

```bash
# 1. Ensure render.yaml is in repo root
# 2. Go to https://dashboard.render.com/
# 3. Click "New +" → "Blueprint"
# 4. Select your GitHub repo
# 5. Render will auto-detect render.yaml
# 6. Review and deploy!
```

## Manual Deployment (if render.yaml not used)

### Backend Deployment
```
1. New Web Service
2. Connect GitHub → select repo
3. Settings:
   - Name: petstore-backend
   - Root Directory: backend
   - Environment: Docker
   - Add env variables:
     * DATABASE_URL: [PostgreSQL connection]
     * CORS_ALLOWED_ORIGINS: [Frontend URL]
4. Deploy
```

### Frontend Deployment
```
1. New Web Service
2. Connect GitHub → select repo
3. Settings:
   - Name: petstore-frontend
   - Root Directory: frontend
   - Environment: Docker
   - Add env variables:
     * VITE_API_BASE_URL: [Backend URL]
4. Deploy
```

## Verify Deployment

```bash
# Backend health check
curl https://petstore-backend.onrender.com/actuator/health

# API test
curl https://petstore-backend.onrender.com/pena/pets

# Frontend access
Open https://petstore-frontend.onrender.com in browser
```

## Troubleshooting

| Issue | Solution |
|-------|----------|
| Backend won't start | Check logs, verify DATABASE_URL format |
| CORS errors | Verify CORS_ALLOWED_ORIGINS includes frontend URL |
| Frontend blank | Check browser console, verify VITE_API_BASE_URL |
| Database connection fails | Check PostgreSQL is running, credentials correct |
| Services inactive after 15min | Free plan - expected, paid plan recommended |

## Support Resources

- [Render Documentation](https://render.com/docs)
- [Spring Boot Production](https://spring.io/guides/gs/spring-boot/)
- [React/Vite Deployment](https://vitejs.dev/guide/static-deploy.html)

---
**See DEPLOYMENT.md for detailed instructions**
