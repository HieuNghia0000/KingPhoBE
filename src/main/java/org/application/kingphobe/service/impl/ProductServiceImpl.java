package org.application.kingphobe.service.impl;

import org.application.kingphobe.dto.ProductDTO;
import org.application.kingphobe.model.Category;
import org.application.kingphobe.model.Product;
import org.application.kingphobe.repository.CategoryRepository;
import org.application.kingphobe.repository.ProductRepository;
import org.application.kingphobe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        mapDtoToProduct(productDTO, product);
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> updateProduct(Integer id, ProductDTO productDTO) {
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            mapDtoToProduct(productDTO, product);
            return Optional.of(productRepository.save(product));
        }

        return Optional.empty();
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    private void mapDtoToProduct(ProductDTO productDTO, Product product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());

        // Fetch the category entity and set it to the product
        Category category = categoryRepository.findById(productDTO.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);
    }
}
