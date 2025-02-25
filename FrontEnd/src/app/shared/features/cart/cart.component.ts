import { CommonModule } from "@angular/common";
import { Component } from '@angular/core';
import { CartService } from 'app/products/data-access/cart.service';
import { ButtonModule } from "primeng/button";
import { DropdownModule } from 'primeng/dropdown';

@Component({
    selector: 'app-cart',
    standalone: true,
    imports: [
        DropdownModule,
        CommonModule,
        ButtonModule,
    ],
    templateUrl: './cart.component.html'
})
export class CartComponent {
    public cart = this.cartService.cart;
    public cartTotalPrice = this.cartService.cartTotalPrice;

    constructor(
        private cartService: CartService
    ) { }

    public onDelete(index: number): void {
        this.cartService.removeFromCart(index);
    }
}
