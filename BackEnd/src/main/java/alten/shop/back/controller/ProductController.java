package alten.shop.back.controller;

import alten.shop.back.model.Product;
import alten.shop.back.service.ProductService;
import alten.shop.back.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private JwtUtil jwtUtil;

	private boolean isAdmin(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
			String email = jwtUtil.extractEmail(token);
			return "admin@admin.com".equals(email);
		}
		return false; // Si le token n'est pas valide ou l'email ne correspond pas, l'utilisateur n'est pas admin
	}

	//Retrieve all products
	@GetMapping
	public List<Product> getAllProducts() throws IOException {
		return productService.getAllProducts();
	}

	//Retrieve details for product 1
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable long id) throws IOException {
		Optional<Product> product = productService.getProductById(id);
		return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}


	//Create a new product
	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody Product product, HttpServletRequest request) throws IOException {
	    if (isAdmin(request)) {
	        Optional<Product> createdProduct = productService.addProduct(product);
	        return createdProduct.map(ResponseEntity::ok)
	                .orElseGet(() -> ResponseEntity.badRequest().build());
	    }
	    
	    return ResponseEntity.status(403).build();
	}

	//Update details of product 1 if it exists
	@PatchMapping("/{id}")
	public ResponseEntity<Product> patchProduct(@PathVariable long id, @RequestBody Product product)
			throws IOException {
		Optional<Product> patchedProduct = productService.patchProduct(id, product);
		return patchedProduct.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	//Remove product 1
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable long id, HttpServletRequest request) throws IOException {
		if (isAdmin(request)) {
			productService.deleteProduct(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(403).build(); 
	}

}


