import CssBaseline from '@mui/material/CssBaseline';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { createContext, useContext, useEffect, useMemo, useState } from 'react';
import { readCart, saveCart } from '../features/cart/cartStorage';
import { readWishlist, saveWishlist } from '../features/wishlist/wishlistStorage';

const theme = createTheme({
  palette: {
    primary: {
      main: '#49654d'
    },
    secondary: {
      main: '#d86c5f'
    },
    background: {
      default: 'transparent'
    }
  },
  shape: {
    borderRadius: 16
  }
});

const ShoppingContext = createContext(null);

export function AppProviders({ children }) {
  const [cartItems, setCartItems] = useState(() => readCart());
  const [wishlistItems, setWishlistItems] = useState(() => readWishlist());

  useEffect(() => {
    saveCart(cartItems);
  }, [cartItems]);

  useEffect(() => {
    saveWishlist(wishlistItems);
  }, [wishlistItems]);

  const value = useMemo(() => ({
    cartItems,
    wishlistItems,
    addToCart(pet) {
      setCartItems((current) => current.some((item) => item.id === pet.id) ? current : [...current, pet]);
    },
    removeFromCart(id) {
      setCartItems((current) => current.filter((item) => item.id !== id));
    },
    addToWishlist(pet) {
      setWishlistItems((current) => current.some((item) => item.id === pet.id) ? current : [...current, pet]);
    },
    removeFromWishlist(id) {
      setWishlistItems((current) => current.filter((item) => item.id !== id));
    }
  }), [cartItems, wishlistItems]);

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <ShoppingContext.Provider value={value}>{children}</ShoppingContext.Provider>
    </ThemeProvider>
  );
}

export function useShopping() {
  const context = useContext(ShoppingContext);
  if (!context) {
    throw new Error('useShopping must be used within AppProviders');
  }
  return context;
}
