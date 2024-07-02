package org.application.kingphobe.service;

import org.application.kingphobe.dto.ProductDTO;
import org.application.kingphobe.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProduct();

    Optional<Product> getProductById(int id);

    Product createProduct(ProductDTO productDTO);

    Optional<Product> updateProduct(Integer id, ProductDTO productDTO);

    void deleteProduct(int id);
}
