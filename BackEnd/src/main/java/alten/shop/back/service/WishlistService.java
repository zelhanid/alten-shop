package alten.shop.back.service;

import alten.shop.back.model.Product;
import alten.shop.back.model.Wishlist;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WishlistService {

    private final Map<String, Wishlist> userWishlists = new HashMap<>();

    public List<Product> getAllProducts(String userEmail) {
        return getWishlist(userEmail).getProducts();
    }

    public Wishlist getWishlist(String userEmail) {
        return userWishlists.computeIfAbsent(userEmail, Wishlist::new);
    }

    public void addProductToWishlist(String userEmail, Product product) {
        Wishlist wishlist = getWishlist(userEmail);
        wishlist.addProduct(product);
    }

    public void removeProductFromWishlist(String userEmail, Product product) {
        Wishlist wishlist = getWishlist(userEmail);
        wishlist.removeProduct(product);
    }


}
