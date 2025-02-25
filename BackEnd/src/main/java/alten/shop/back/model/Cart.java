package alten.shop.back.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {
    private String userEmail;
    private List<CartItem> items = new ArrayList<>();

    public Cart(String userEmail) {
        this.userEmail = userEmail;
    }

    public void addItem(CartItem item) {
        for (CartItem existingItem : items) {
            if (existingItem.getProductId() == item.getProductId()) {
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }

    public void removeItem(long productId) {
        items.removeIf(item -> item.getProductId() == productId);
    }
}
