const KEY = 'petstore.wishlist';

export function readWishlist() {
  return JSON.parse(localStorage.getItem(KEY) || '[]');
}

export function saveWishlist(items) {
  localStorage.setItem(KEY, JSON.stringify(items));
}
