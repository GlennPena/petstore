import { Box, Button, Card, CardContent, Container, Grid, Stack, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useShopping } from '../components/AppProviders';

export default function WishlistPage() {
  const navigate = useNavigate();
  const { wishlistItems, removeFromWishlist, addToCart } = useShopping();

  if (wishlistItems.length === 0) {
    return (
      <Container sx={{ py: 4 }}>
        <Box sx={{ textAlign: 'center', py: 6 }}>
          <Typography variant="h5" sx={{ mb: 2 }}>Your wishlist is empty</Typography>
          <Typography variant="body1" color="textSecondary" sx={{ mb: 4 }}>
            Save your favorite pets to your wishlist!
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
      <Typography variant="h4" sx={{ mb: 4 }}>My Wishlist</Typography>
      
      <Grid container spacing={3}>
        {wishlistItems.map((item) => (
          <Grid item xs={12} sm={6} md={4} key={item.id}>
            <Card sx={{ display: 'flex', flexDirection: 'column', height: '100%' }}>
              <Box
                component="img"
                src={item.imageUrl}
                alt={item.name}
                sx={{
                  width: '100%',
                  height: 200,
                  objectFit: 'cover',
                }}
              />
              <CardContent sx={{ flex: 1, display: 'flex', flexDirection: 'column' }}>
                <Box sx={{ display: 'flex', gap: 1, mb: 1 }}>
                  <Typography variant="caption" sx={{ px: 1, py: 0.5, bgcolor: '#e3f2fd', borderRadius: '4px' }}>
                    {item.category}
                  </Typography>
                  <Typography
                    variant="caption"
                    sx={{
                      px: 1,
                      py: 0.5,
                      bgcolor: item.availability === 'AVAILABLE' ? '#e8f5e9' : '#ffebee',
                      borderRadius: '4px',
                      color: item.availability === 'AVAILABLE' ? '#2e7d32' : '#c62828',
                    }}
                  >
                    {item.availability}
                  </Typography>
                </Box>
                <Typography variant="h6" sx={{ mb: 1 }}>{item.name}</Typography>
                <Typography variant="body2" color="textSecondary" sx={{ mb: 2, flex: 1 }}>
                  {item.description}
                </Typography>
                <Typography variant="h6" sx={{ mb: 2, fontWeight: 'bold' }}>
                  ${item.price}
                </Typography>
                <Stack spacing={1}>
                  <Button
                    variant="contained"
                    fullWidth
                    disabled={item.availability !== 'AVAILABLE'}
                    onClick={() => {
                      addToCart(item);
                      removeFromWishlist(item.id);
                    }}
                  >
                    Move to Cart
                  </Button>
                  <Button
                    variant="outlined"
                    color="error"
                    fullWidth
                    onClick={() => removeFromWishlist(item.id)}
                  >
                    Remove
                  </Button>
                </Stack>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Container>
  );
}
