package org.application.kingphobe.service;

import org.application.kingphobe.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO saveProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Integer productId, ProductDTO productDTO);
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Integer id);
    void deleteProduct(Integer id);
}
