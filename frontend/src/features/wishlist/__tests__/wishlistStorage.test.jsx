import { afterEach, beforeEach, describe, expect, it } from 'vitest';
import { readWishlist, saveWishlist } from '../wishlistStorage';

describe('wishlistStorage', () => {
  beforeEach(() => {
    localStorage.clear();
  });

  afterEach(() => {
    localStorage.clear();
  });

  it('reads an empty wishlist by default', () => {
    expect(readWishlist()).toEqual([]);
  });

  it('saves wishlist items to localStorage', () => {
    saveWishlist([{ id: 2, name: 'Mittens' }]);
    expect(JSON.parse(localStorage.getItem('petstore.wishlist'))).toEqual([{ id: 2, name: 'Mittens' }]);
    expect(readWishlist()).toEqual([{ id: 2, name: 'Mittens' }]);
  });
});
