package alten.shop.back.repository;

import java.io.IOException;
import org.springframework.stereotype.Repository;

import alten.shop.back.model.Product;
import alten.shop.back.util.JsonUtil;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final JsonUtil jsonFileUtil;

    public ProductRepository(JsonUtil jsonFileUtil) {
        this.jsonFileUtil = jsonFileUtil;
    }

    public List<Product> findAll() throws IOException {
        return jsonFileUtil.readJsonFile(Product.class);
    }

    public Optional<Product> findById(Long id) throws IOException {
    	return findAll().stream().filter(p -> p.getId() == id).findFirst();
    }

    public void save(Product product) throws IOException {
        List<Product> products = findAll();
        products.add(product);
        jsonFileUtil.writeJsonFile(products);
    }



    public void delete(Long id) throws IOException {
        List<Product> products = findAll();
        products.removeIf(p -> p.getId() ==(id));
        jsonFileUtil.writeJsonFile(products);
    }



}