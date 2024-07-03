package org.application.kingphobe.service.impl;

import org.application.kingphobe.dto.CategoryDTO;
import org.application.kingphobe.dto.ProductDTO;
import org.application.kingphobe.model.Category;
import org.application.kingphobe.model.Product;
import org.application.kingphobe.model.ProductImage;
import org.application.kingphobe.repository.CategoryRepository;
import org.application.kingphobe.repository.ProductImageRepository;
import org.application.kingphobe.repository.ProductRepository;
import org.application.kingphobe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        product = productRepository.save(product);
        saveProductImages(product, productDTO.getImageUrls());
        return convertToDTO(product);
    }

    @Override
    public ProductDTO updateProduct(Integer productId, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());

        if (productDTO.getCategory() != null) {
            Category category = categoryRepository.findById(productDTO.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingProduct.setCategory(category);
        } else {
            existingProduct.setCategory(null);
        }

        productRepository.save(existingProduct);
        updateProductImages(existingProduct, productDTO.getImageUrls());

        return convertToDTO(existingProduct);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToDTO(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());

        if (productDTO.getCategory() != null) {
            Category category = categoryRepository.findById(productDTO.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }

        return product;
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getProductId().intValue());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategory(
                product.getCategory() != null ? convertCategoryToDTO(product.getCategory()) : null
        );
        productDTO.setImageUrls(
                product.getImages() == null ?
                        new ArrayList<>() :
                        product.getImages().stream().map(ProductImage::getImageUrl).collect(Collectors.toList())
        );
        return productDTO;
    }

    private CategoryDTO convertCategoryToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getCategoryId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        return categoryDTO;
    }

    private void saveProductImages(Product product, List<String> imageUrls) {
        imageUrls.forEach(url -> {
            ProductImage image = new ProductImage();
            image.setProduct(product);
            image.setImageUrl(url);
            productImageRepository.save(image);
        });
    }

    private void updateProductImages(Product product, List<String> imageUrls) {
        // First, delete existing images
        List<ProductImage> existingImages = productImageRepository.findByProduct(product);
        productImageRepository.deleteAll(existingImages);

        // Then, save new images
        saveProductImages(product, imageUrls);
    }
}