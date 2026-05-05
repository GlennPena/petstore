import { Box, Button, Card, CardContent, Container, Grid, Stack, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useShopping } from '../components/AppProviders';

export default function CartPage() {
  const navigate = useNavigate();
  const { cartItems, removeFromCart } = useShopping();

  const total = cartItems.reduce((sum, item) => sum + parseFloat(item.price), 0);

  if (cartItems.length === 0) {
    return (
      <Container sx={{ py: 4 }}>
        <Box sx={{ textAlign: 'center', py: 6 }}>
          <Typography variant="h5" sx={{ mb: 2 }}>Your cart is empty</Typography>
          <Typography variant="body1" color="textSecondary" sx={{ mb: 4 }}>
            Browse our catalog and add some pets to your cart!
          </Typography>
          <Button variant="contained" onClick={() => navigate('/')}>
            Back to Catalog
          </Button>
        </Box>
      </Container>
    );
  }

  return (
    <Container sx={{ py: 4 }}>
      <Typography variant="h4" sx={{ mb: 4 }}>Shopping Cart</Typography>
      
      <Grid container spacing={4}>
        <Grid item xs={12} md={8}>
          <Stack spacing={2}>
            {cartItems.map((item) => (
              <Card key={item.id} sx={{ display: 'flex' }}>
                <Box
                  component="img"
                  src={item.imageUrl}
                  alt={item.name}
                  sx={{
                    width: 120,
                    height: 120,
                    objectFit: 'cover',
                    flexShrink: 0,
                  }}
                />
                <CardContent sx={{ flex: 1, display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                  <Box>
                    <Typography variant="h6">{item.name}</Typography>
                    <Typography variant="body2" color="textSecondary">
                      {item.category}
                    </Typography>
                    <Typography variant="body1" sx={{ mt: 1, fontWeight: 'bold' }}>
                      ${item.price}
                    </Typography>
                  </Box>
                  <Button
                    variant="outlined"
                    color="error"
                    size="small"
                    onClick={() => removeFromCart(item.id)}
                  >
                    Remove
                  </Button>
                </CardContent>
              </Card>
            ))}
          </Stack>
        </Grid>

        <Grid item xs={12} md={4}>
          <Card>
            <CardContent>
              <Typography variant="h6" sx={{ mb: 2 }}>Order Summary</Typography>
              <Box sx={{ display: 'flex', justifyContent: 'space-between', mb: 2 }}>
                <Typography>Subtotal:</Typography>
                <Typography>${total.toFixed(2)}</Typography>
              </Box>
              <Box sx={{ display: 'flex', justifyContent: 'space-between', mb: 3, fontWeight: 'bold' }}>
                <Typography>Total:</Typography>
                <Typography>${total.toFixed(2)}</Typography>
              </Box>
              <Button fullWidth variant="contained" disabled sx={{ mb: 2 }}>
                Checkout (Coming Soon)
              </Button>
              <Button fullWidth variant="outlined" onClick={() => navigate('/')}>
                Continue Shopping
              </Button>
            </CardContent>
          </Card>
        </Grid>
      </Grid>
    </Container>
  );
}
