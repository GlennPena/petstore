# 🐾 Petstore - Render Deployment Ready

A full-stack pet store application built with Spring Boot and React, configured for production deployment on Render.com.

## 🚀 Quick Start

### Fastest Path to Production (5 minutes)

1. **Prepare your environment:**
   ```bash
   # Windows
   scripts\prepare-render-deployment.bat
   
   # macOS/Linux
   bash scripts/prepare-render-deployment.sh
   ```

2. **Push to GitHub:**
   ```bash
   git push origin main
   ```

3. **Deploy to Render:**
   - Go to https://dashboard.render.com/
   - Click "New +" → "Blueprint"
   - Select your GitHub repository
   - Click "Deploy"

✅ **Done!** Your app will be live in ~5 minutes.

---

## 📋 Complete Deployment Checklist

For detailed step-by-step instructions:
👉 **[RENDER_DEPLOY_CHECKLIST.md](RENDER_DEPLOY_CHECKLIST.md)**

---

## 📚 Documentation

| Document | Purpose |
|----------|---------|
| [RENDER_DEPLOY_CHECKLIST.md](RENDER_DEPLOY_CHECKLIST.md) | Step-by-step deployment guide |
| [RENDER_QUICKSTART.md](RENDER_QUICKSTART.md) | Quick reference guide |
| [DEPLOYMENT.md](DEPLOYMENT.md) | Detailed deployment guide with troubleshooting |
| [DEPLOYMENT_SUMMARY.md](DEPLOYMENT_SUMMARY.md) | Configuration overview |
| [.env.example](.env.example) | Environment variables reference |

---

## 🏗️ Architecture

### Backend
- **Framework:** Spring Boot 3.3.5
- **Database:** PostgreSQL (production) / H2 (development)
- **API:** REST endpoints at `/pena/pets`
- **Port:** Dynamic (environment variable)

### Frontend
- **Framework:** React 18 + Vite
- **Styling:** Tailwind CSS 3 + MUI 6
- **Port:** 5173 (development) / 80 (production)

### Infrastructure
- **Hosting:** Render.com
- **Database:** PostgreSQL on Render
- **CI/CD:** GitHub Actions
- **Containers:** Docker

---

## 🎯 Features

✅ **Pet Catalog** - Browse available pets with filters
✅ **Pet Details** - View detailed information about each pet
✅ **Shopping Cart** - Add/remove items, persistent storage
✅ **Wishlist** - Save favorites for later
✅ **Responsive UI** - Mobile-friendly design
✅ **Production Ready** - Environment-based configuration
✅ **Automated Tests** - Backend and frontend tests
✅ **CI/CD Pipeline** - Automatic deployment

---

## 🛠️ Development

### Prerequisites
- Java 17
- Node.js 20
- Maven 3.9.9
- Git

### Setup

**Backend:**
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

**Frontend:**
```bash
cd frontend
npm install
npm run dev
```

### Testing

**Backend:**
```bash
cd backend
mvn test
```

**Frontend:**
```bash
cd frontend
npm test
```

---

## 📦 Project Structure

```
petstore/
├── backend/                          # Spring Boot application
│   ├── src/main/java/               # Java source code
│   ├── src/test/                    # Backend tests
│   ├── pom.xml                      # Maven configuration
│   ├── Dockerfile                   # Docker build configuration
│   └── application-prod.yml         # Production configuration
│
├── frontend/                         # React + Vite application
│   ├── src/                         # React components and pages
│   ├── public/                      # Static assets
│   ├── package.json                 # Node dependencies
│   ├── vite.config.js              # Vite configuration
│   ├── Dockerfile                   # Docker build configuration
│   └── vitest.config.js            # Test configuration
│
├── .github/
│   ├── workflows/ci-cd.yml          # GitHub Actions automation
│   └── copilot-instructions.md      # Copilot guidelines
│
├── scripts/
│   ├── prepare-render-deployment.sh # Linux/macOS setup script
│   └── prepare-render-deployment.bat # Windows setup script
│
├── render.yaml                      # Render deployment configuration
├── DEPLOYMENT.md                    # Detailed deployment guide
├── DEPLOYMENT_SUMMARY.md            # Configuration overview
├── RENDER_DEPLOY_CHECKLIST.md      # Step-by-step checklist
├── RENDER_QUICKSTART.md            # Quick reference
└── .env.example                     # Environment variables template
```

---

## 🔧 Configuration

### Environment Variables

**Backend:**
- `DATABASE_URL` - PostgreSQL connection string
- `CORS_ALLOWED_ORIGINS` - Comma-separated allowed origins
- `SPRING_PROFILES_ACTIVE` - Set to `prod` for production
- `SERVER_PORT` - Port number (default: 8080)

**Frontend:**
- `VITE_API_BASE_URL` - Backend API URL

See [.env.example](.env.example) for all variables.

---

## 🚨 Troubleshooting

### Issue: Backend won't start
**Solution:** Check `DATABASE_URL` format and ensure PostgreSQL is running

### Issue: Frontend shows blank page
**Solution:** Check `VITE_API_BASE_URL` and browser console for errors

### Issue: CORS errors
**Solution:** Verify `CORS_ALLOWED_ORIGINS` includes the correct frontend URL

### Issue: Services won't deploy
**Solution:** Check Render logs for build errors or resource limits

👉 **Full troubleshooting guide in [DEPLOYMENT.md](DEPLOYMENT.md)**

---

## 📈 Performance

- **Frontend Build:** ~2.6 seconds
- **Backend Build:** ~30 seconds
- **Total Deploy Time:** ~5 minutes
- **Load Time:** <2 seconds (first request may be slower on free tier)

---

## 🔐 Security

✅ HTTPS/SSL enabled automatically on Render
✅ Environment variables for sensitive data
✅ CORS properly configured
✅ Database credentials in environment variables
✅ No hardcoded secrets in code

---

## 📊 Database

### Schema
The database is initialized with 6 sample pets on first startup:
- Buddy (Dog, $499)
- Mittens (Cat, $299)
- Rio (Bird, $149)
- Bubbles (Fish, $39 - Unavailable)
- Luna (Cat, $349)
- Kiki (Bird, $179)

### Persistence
- **Development:** H2 in-memory database
- **Production:** PostgreSQL with persistent storage

---

## 🎓 Learning Resources

- [Render Documentation](https://render.com/docs)
- [Spring Boot Guide](https://spring.io/guides/gs/spring-boot/)
- [React Documentation](https://react.dev)
- [Vite Documentation](https://vitejs.dev)

---

## 📝 License

[Add your license information here]

---

## 🤝 Contributing

[Add contributing guidelines here]

---

## ❓ Questions?

1. Check the [troubleshooting section](#-troubleshooting) above
2. Read [DEPLOYMENT.md](DEPLOYMENT.md) for detailed guidance
3. Review [RENDER_DEPLOY_CHECKLIST.md](RENDER_DEPLOY_CHECKLIST.md) for step-by-step instructions
4. Check Render's [documentation](https://render.com/docs)

---

## ✅ Deployment Readiness

- ✅ All code compiled successfully
- ✅ All tests passing
- ✅ Docker images buildable
- ✅ Environment variables configured
- ✅ Database migrations prepared
- ✅ CORS configuration ready
- ✅ Health check endpoints available

**Status: Ready for Production Deployment** 🚀

---

**Last Updated:** 2024
**Status:** Production Ready
