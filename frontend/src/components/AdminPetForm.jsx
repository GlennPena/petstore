import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import MenuItem from '@mui/material/MenuItem';
import Stack from '@mui/material/Stack';
import TextField from '@mui/material/TextField';

const categories = ['DOGS', 'CATS', 'BIRDS', 'FISHES'];
const availability = ['AVAILABLE', 'UNAVAILABLE'];

export default function AdminPetForm({ value, onChange, onSubmit, submitLabel = 'Save pet' }) {
  return (
    <Box component="form" onSubmit={onSubmit} sx={{ display: 'grid', gap: 2, gridTemplateColumns: { xs: '1fr', md: '1fr 1fr' } }}>
      <TextField label="Name" value={value.name} onChange={(event) => onChange({ ...value, name: event.target.value })} required />
      <TextField select label="Category" value={value.category} onChange={(event) => onChange({ ...value, category: event.target.value })} required>
        {categories.map((category) => <MenuItem key={category} value={category}>{category}</MenuItem>)}
      </TextField>
      <TextField label="Age in months" type="number" value={value.ageMonths} onChange={(event) => onChange({ ...value, ageMonths: event.target.value })} required />
      <TextField label="Price" type="number" inputProps={{ step: '0.01' }} value={value.price} onChange={(event) => onChange({ ...value, price: event.target.value })} required />
      <TextField select label="Availability" value={value.availability} onChange={(event) => onChange({ ...value, availability: event.target.value })} required>
        {availability.map((item) => <MenuItem key={item} value={item}>{item}</MenuItem>)}
      </TextField>
      <TextField label="Image URL" value={value.imageUrl} onChange={(event) => onChange({ ...value, imageUrl: event.target.value })} />
      <TextField label="Description" value={value.description} onChange={(event) => onChange({ ...value, description: event.target.value })} multiline minRows={4} sx={{ gridColumn: '1 / -1' }} />
      <Stack direction="row" spacing={2} sx={{ gridColumn: '1 / -1' }}>
        <Button type="submit" variant="contained">{submitLabel}</Button>
      </Stack>
    </Box>
  );
}
