import Alert from '@mui/material/Alert';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Divider from '@mui/material/Divider';
import Grid from '@mui/material/Grid';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';
import { useEffect, useState } from 'react';
import AdminPetForm from '../components/AdminPetForm';
import EmptyState from '../components/EmptyState';
import { createPet, deletePet, getPets, updatePet } from '../services/petsApi';

const emptyForm = {
  name: '',
  category: 'DOGS',
  ageMonths: '',
  price: '',
  availability: 'AVAILABLE',
  description: '',
  imageUrl: ''
};

export default function AdminPetManagementPage() {
  const [pets, setPets] = useState([]);
  const [form, setForm] = useState(emptyForm);
  const [selectedPet, setSelectedPet] = useState(null);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const refresh = () => {
    getPets().then(setPets).catch((caught) => setError(caught.message));
  };

  useEffect(() => {
    refresh();
  }, []);

  const submit = async (event) => {
    event.preventDefault();
    setError('');
    try {
      const payload = {
        ...form,
        ageMonths: Number(form.ageMonths),
        price: Number(form.price)
      };
      if (selectedPet) {
        await updatePet(selectedPet.id, payload);
        setMessage('Pet updated.');
      } else {
        await createPet(payload);
        setMessage('Pet created.');
      }
      setForm(emptyForm);
      setSelectedPet(null);
      refresh();
    } catch (caught) {
      setError(caught.message);
    }
  };

  const edit = (pet) => {
    setSelectedPet(pet);
    setForm({
      name: pet.name,
      category: pet.category,
      ageMonths: pet.ageMonths,
      price: pet.price,
      availability: pet.availability,
      description: pet.description || '',
      imageUrl: pet.imageUrl || ''
    });
  };

  const remove = async (id) => {
    setError('');
    await deletePet(id);
    setMessage('Pet deleted.');
    refresh();
  };

  return (
    <Stack spacing={3}>
      <Box>
        <Typography variant="h4" fontWeight={900}>Admin pet management</Typography>
        <Typography variant="body1" color="text.secondary">Create, update, and delete catalog listings.</Typography>
      </Box>

      {message && <Alert severity="success" onClose={() => setMessage('')}>{message}</Alert>}
      {error && <Alert severity="error" onClose={() => setError('')}>{error}</Alert>}

      <Card sx={{ p: 2 }}>
        <CardContent>
          <Typography variant="h6" fontWeight={800} sx={{ mb: 2 }}>{selectedPet ? 'Edit pet' : 'Create pet'}</Typography>
          <AdminPetForm value={form} onChange={setForm} onSubmit={submit} submitLabel={selectedPet ? 'Update pet' : 'Create pet'} />
        </CardContent>
      </Card>

      <Divider />

      {pets.length === 0 ? (
        <EmptyState title="No catalog entries yet" description="Create the first pet listing using the form above." />
      ) : (
        <Grid container spacing={2}>
          {pets.map((pet) => (
            <Grid item xs={12} md={6} key={pet.id}>
              <Card>
                <CardContent>
                  <Typography variant="h6" fontWeight={700}>{pet.name}</Typography>
                  <Typography variant="body2" color="text.secondary">{pet.category} · {pet.availability}</Typography>
                  <Typography variant="body2">${Number(pet.price).toFixed(2)}</Typography>
                  <Stack direction="row" spacing={1} sx={{ mt: 2 }}>
                    <Button variant="outlined" onClick={() => edit(pet)}>Edit</Button>
                    <Button color="error" variant="outlined" onClick={() => remove(pet.id)}>Delete</Button>
                  </Stack>
                </CardContent>
              </Card>
            </Grid>
          ))}
        </Grid>
      )}
    </Stack>
  );
}
