import {computed, Injectable, signal} from '@angular/core';
import {Product} from './product.model';

type Cart = Product[];

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private storageKey = 'shoppingCart';

  private writableCart = signal(this.getCart());
  public readonly cart = this.writableCart.asReadonly();
  public readonly cartSize = computed(() => this.writableCart().length);
  public readonly cartTotalPrice = computed(() => {
    return this.writableCart().reduce((acc, product) => acc + product.price, 0);
  });

  constructor() {
  }

  getCart(): Cart {
    const cart = localStorage.getItem(this.storageKey);
    return cart ? JSON.parse(cart) : [];
  }

  addToCart(product: Product) {
    const cart = this.getCart();
    cart.push(product);
    this.updateCart(cart);
  }

  removeFromCart(index: number): void {
    const cart = this.getCart();
    cart.splice(index, 1);
    this.updateCart(cart);
  }

  private updateCart(cart: Product[]): void {
    localStorage.setItem(this.storageKey, JSON.stringify(cart));
    this.writableCart.set(cart);
  }
}
