import Box from '@mui/material/Box';
import CircularProgress from '@mui/material/CircularProgress';
import Grid from '@mui/material/Grid';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';
import { useEffect, useState } from 'react';
import CatalogFilters from '../components/CatalogFilters';
import EmptyState from '../components/EmptyState';
import PetCard from '../components/PetCard';
import { getPets } from '../services/petsApi';

const initialFilters = {
  search: '',
  category: '',
  minPrice: '',
  maxPrice: '',
  availability: ''
};

export default function CatalogPage() {
  const [filters, setFilters] = useState(initialFilters);
  const [pets, setPets] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    setLoading(true);
    getPets(filters)
      .then(setPets)
      .catch((caught) => setError(caught.message))
      .finally(() => setLoading(false));
  }, [filters]);

  return (
    <Stack spacing={3}>
      <Box>
        <Typography variant="overline" color="secondary">Petstore</Typography>
        <Typography variant="h3" fontWeight={900}>Find pets that fit your home.</Typography>
        <Typography variant="body1" color="text.secondary">Browse the catalog, filter what matters, and save pets you like.</Typography>
      </Box>

      <CatalogFilters value={filters} onChange={setFilters} onSubmit={(event) => event.preventDefault()} />

      {loading && <Box sx={{ display: 'flex', justifyContent: 'center', py: 8 }}><CircularProgress /></Box>}
      {!loading && error && <EmptyState title="Something went wrong" description={error} />}
      {!loading && !error && pets.length === 0 && <EmptyState title="No pets found" description="Try adjusting your filters or search terms." />}
      {!loading && !error && pets.length > 0 && (
        <Grid container spacing={3}>
          {pets.map((pet) => (
            <Grid item xs={12} sm={6} md={4} key={pet.id}>
              <PetCard pet={pet} />
            </Grid>
          ))}
        </Grid>
      )}
    </Stack>
  );
}
