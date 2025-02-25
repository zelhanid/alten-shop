import {
  Component,
} from "@angular/core";
import {RouterModule} from "@angular/router";
import {SplitterModule} from 'primeng/splitter';
import {ToolbarModule} from 'primeng/toolbar';
import {PanelMenuComponent} from "./shared/ui/panel-menu/panel-menu.component";
import {CartService} from "./products/data-access/cart.service";
import {BadgeModule} from "primeng/badge";
import {ToastModule} from "primeng/toast";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
  imports: [RouterModule, SplitterModule, ToolbarModule, PanelMenuComponent, BadgeModule, ToastModule],
})
export class AppComponent {
  title = "ALTEN SHOP";

  constructor(private cartService: CartService) {
  }

  cartSize = this.cartService.cartSize;

}
