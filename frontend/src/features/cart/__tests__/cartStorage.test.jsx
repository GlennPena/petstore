import { afterEach, beforeEach, describe, expect, it } from 'vitest';
import { readCart, saveCart } from '../cartStorage';

describe('cartStorage', () => {
  beforeEach(() => {
    localStorage.clear();
  });

  afterEach(() => {
    localStorage.clear();
  });

  it('reads an empty cart by default', () => {
    expect(readCart()).toEqual([]);
  });

  it('saves cart items to localStorage', () => {
    saveCart([{ id: 1, name: 'Buddy' }]);
    expect(JSON.parse(localStorage.getItem('petstore.cart'))).toEqual([{ id: 1, name: 'Buddy' }]);
    expect(readCart()).toEqual([{ id: 1, name: 'Buddy' }]);
  });
});
