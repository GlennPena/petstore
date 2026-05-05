import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';

export default function EmptyState({ title, description }) {
  return (
    <Box sx={{ py: 8, textAlign: 'center' }}>
      <Typography variant="h5" fontWeight={700}>{title}</Typography>
      <Typography variant="body1" sx={{ mt: 1, color: 'text.secondary' }}>{description}</Typography>
    </Box>
  );
}
