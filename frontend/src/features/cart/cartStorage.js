const KEY = 'petstore.cart';

export function readCart() {
  return JSON.parse(localStorage.getItem(KEY) || '[]');
}

export function saveCart(items) {
  localStorage.setItem(KEY, JSON.stringify(items));
}
