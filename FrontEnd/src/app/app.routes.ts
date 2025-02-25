import { Routes } from "@angular/router";
import { HomeComponent } from "./shared/features/home/home.component";
import {CartComponent} from "./shared/features/cart/cart.component";
import {ContactComponent} from "./shared/features/contact/contact.component";

export const APP_ROUTES: Routes = [
  {
    path: "home",
    component: HomeComponent,
  },
  {
    path: "products",
    loadChildren: () =>
      import("./products/products.routes").then((m) => m.PRODUCTS_ROUTES)
  },
  {
    path: "cart",
    component: CartComponent,
  },
  //Partie 2 - Créer un nouveau point de menu dans la barre latérale ("Contact")
  {
    path: "contact",
    component: ContactComponent,
  },
  { path: "", redirectTo: "home", pathMatch: "full" },
];
