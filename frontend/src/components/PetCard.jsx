import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Chip from '@mui/material/Chip';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import { Link as RouterLink } from 'react-router-dom';
import { useShopping } from './AppProviders';

export default function PetCard({ pet }) {
  const { addToCart, addToWishlist } = useShopping();

  return (
    <Card sx={{ height: '100%', display: 'flex', flexDirection: 'column', borderRadius: 4 }}>
      <CardMedia component="img" height="220" image={pet.imageUrl || 'https://images.unsplash.com/photo-1517841905240-472988babdf9'} alt={pet.name} />
      <CardContent sx={{ flexGrow: 1 }}>
        <Stack direction="row" spacing={1} sx={{ mb: 1, flexWrap: 'wrap' }}>
          <Chip size="small" label={pet.category} color="primary" />
          <Chip size="small" label={pet.availability} color={pet.availability === 'AVAILABLE' ? 'success' : 'default'} />
        </Stack>
        <Typography variant="h6" fontWeight={700}>{pet.name}</Typography>
        <Typography variant="body2" color="text.secondary">{pet.description}</Typography>
        <Typography variant="body1" sx={{ mt: 2 }} fontWeight={700}>${Number(pet.price).toFixed(2)}</Typography>
      </CardContent>
      <CardActions sx={{ p: 2, pt: 0, gap: 1, flexWrap: 'wrap' }}>
        <Button component={RouterLink} to={`/pets/${pet.id}`} variant="outlined" size="small">View details</Button>
        <Button size="small" variant="contained" onClick={() => addToCart(pet)} disabled={pet.availability !== 'AVAILABLE'}>Add to cart</Button>
        <Button size="small" color="secondary" variant="contained" onClick={() => addToWishlist(pet)}>Wishlist</Button>
      </CardActions>
    </Card>
  );
}
