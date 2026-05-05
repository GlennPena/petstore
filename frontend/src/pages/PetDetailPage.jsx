import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Chip from '@mui/material/Chip';
import CircularProgress from '@mui/material/CircularProgress';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';
import { useEffect, useState } from 'react';
import { Link as RouterLink, useParams } from 'react-router-dom';
import EmptyState from '../components/EmptyState';
import { useShopping } from '../components/AppProviders';
import { getPet } from '../services/petsApi';

export default function PetDetailPage() {
  const { id } = useParams();
  const { addToCart, addToWishlist } = useShopping();
  const [pet, setPet] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    setLoading(true);
    getPet(id)
      .then(setPet)
      .catch((caught) => setError(caught.message))
      .finally(() => setLoading(false));
  }, [id]);

  if (loading) {
    return <Box sx={{ display: 'flex', justifyContent: 'center', py: 8 }}><CircularProgress /></Box>;
  }

  if (error) {
    return <EmptyState title="Pet not available" description={error} />;
  }

  if (!pet) {
    return <EmptyState title="No pet found" description="This listing may have been removed." />;
  }

  return (
    <Stack spacing={3}>
      <Button component={RouterLink} to="/" variant="text">Back to catalog</Button>
      <Box sx={{ display: 'grid', gap: 3, gridTemplateColumns: { xs: '1fr', md: '1fr 1fr' }, alignItems: 'start' }}>
        <Box component="img" src={pet.imageUrl || 'https://images.unsplash.com/photo-1517841905240-472988babdf9'} alt={pet.name} sx={{ width: '100%', borderRadius: 4 }} />
        <Stack spacing={2}>
          <Stack direction="row" spacing={1}>
            <Chip label={pet.category} color="primary" />
            <Chip label={pet.availability} color={pet.availability === 'AVAILABLE' ? 'success' : 'default'} />
          </Stack>
          <Typography variant="h3" fontWeight={900}>{pet.name}</Typography>
          <Typography variant="body1" color="text.secondary">{pet.description}</Typography>
          <Typography variant="h5" fontWeight={800}>${Number(pet.price).toFixed(2)}</Typography>
          <Typography variant="body2" color="text.secondary">Age: {pet.ageMonths} months</Typography>
          <Stack direction="row" spacing={2} flexWrap="wrap">
            <Button variant="contained" onClick={() => addToCart(pet)} disabled={pet.availability !== 'AVAILABLE'}>Add to cart</Button>
            <Button variant="outlined" color="secondary" onClick={() => addToWishlist(pet)}>Add to wishlist</Button>
          </Stack>
        </Stack>
      </Box>
    </Stack>
  );
}
