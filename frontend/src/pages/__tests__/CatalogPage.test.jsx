import { render, screen, waitFor } from '@testing-library/react';
import { describe, expect, it, vi } from 'vitest';
import { BrowserRouter } from 'react-router-dom';
import { AppProviders } from '../../components/AppProviders';
import CatalogPage from '../CatalogPage';

vi.mock('../../services/petsApi', () => ({
  getPets: vi.fn()
}));

import { getPets } from '../../services/petsApi';

describe('CatalogPage', () => {
  it('renders catalog results returned by the API', async () => {
    getPets.mockResolvedValue([
      {
        id: 1,
        name: 'Buddy',
        category: 'DOGS',
        ageMonths: 12,
        price: 499,
        availability: 'AVAILABLE',
        description: 'Friendly puppy',
        imageUrl: ''
      }
    ]);

    render(
      <BrowserRouter>
        <AppProviders>
          <CatalogPage />
        </AppProviders>
      </BrowserRouter>
    );

    await waitFor(() => expect(screen.getByText('Buddy')).toBeInTheDocument());
    expect(screen.getByText('DOGS')).toBeInTheDocument();
    expect(getPets).toHaveBeenCalledWith({ search: '', category: '', minPrice: '', maxPrice: '', availability: '' });
  });
});
