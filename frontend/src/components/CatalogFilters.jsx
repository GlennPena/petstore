import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import MenuItem from '@mui/material/MenuItem';
import TextField from '@mui/material/TextField';

const categories = ['','DOGS','CATS','BIRDS','FISHES'];
const availability = ['', 'AVAILABLE', 'UNAVAILABLE'];

export default function CatalogFilters({ value, onChange, onSubmit }) {
  return (
    <Box component="form" onSubmit={onSubmit} sx={{ display: 'grid', gap: 2, gridTemplateColumns: { xs: '1fr', md: 'repeat(6, 1fr)' }, mb: 3 }}>
      <TextField label="Search" value={value.search} onChange={(event) => onChange({ ...value, search: event.target.value })} />
      <TextField select label="Category" value={value.category} onChange={(event) => onChange({ ...value, category: event.target.value })}>
        <MenuItem value="">All</MenuItem>
        {categories.filter(Boolean).map((category) => <MenuItem key={category} value={category}>{category}</MenuItem>)}
      </TextField>
      <TextField label="Min price" type="number" value={value.minPrice} onChange={(event) => onChange({ ...value, minPrice: event.target.value })} />
      <TextField label="Max price" type="number" value={value.maxPrice} onChange={(event) => onChange({ ...value, maxPrice: event.target.value })} />
      <TextField select label="Availability" value={value.availability} onChange={(event) => onChange({ ...value, availability: event.target.value })}>
        <MenuItem value="">All</MenuItem>
        {availability.filter(Boolean).map((item) => <MenuItem key={item} value={item}>{item}</MenuItem>)}
      </TextField>
      <Button type="submit" variant="contained" sx={{ alignSelf: 'center', height: '56px' }}>Apply</Button>
    </Box>
  );
}
