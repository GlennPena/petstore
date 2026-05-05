import { Navigate, Route, Routes } from 'react-router-dom';
import { AppShell } from './components/layout/AppShell';
import CatalogPage from './pages/CatalogPage';
import PetDetailPage from './pages/PetDetailPage';
import CartPage from './pages/CartPage';
import WishlistPage from './pages/WishlistPage';

export default function App() {
  return (
    <AppShell>
      <Routes>
        <Route path="/" element={<CatalogPage />} />
        <Route path="/pets/:id" element={<PetDetailPage />} />
        <Route path="/cart" element={<CartPage />} />
        <Route path="/wishlist" element={<WishlistPage />} />
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </AppShell>
  );
}
