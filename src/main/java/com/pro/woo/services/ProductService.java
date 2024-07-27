package com.pro.woo.services;

import com.pro.woo.dtos.ProductDTO;
import com.pro.woo.dtos.ProductImageDTO;
import com.pro.woo.exceptions.DataNotFoundException;
import com.pro.woo.models.Category;
import com.pro.woo.models.Product;
import com.pro.woo.models.ProductImage;
import com.pro.woo.repositories.CategoryRepository;
import com.pro.woo.repositories.ProductImageRepository;
import com.pro.woo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public Product createProduct(ProductDTO productDTO) throws DataNotFoundException{
        Category existingCategory = categoryRepository.
                findById(productDTO.getCategoryId())
                .orElseThrow(()-> new DataNotFoundException("Category not found"));
        Product newProduct =  Product
                .builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .thumbnail(productDTO.getThumbnail())
                .description(productDTO.getDescription())
                .category(existingCategory)
                .build();
        return productRepository.save(newProduct);
    }

    @Override
    public Product getProductById(Long id) throws Exception {
        return productRepository.findById(id).orElseThrow(()-> new DataNotFoundException("Product not found"));
    }

    @Override
    public Page<Product> getAllProducts(PageRequest pageRequest) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDTO) throws Exception {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public boolean existsByName(String name) {
        return false;
    }

    @Override
    public ProductImage createProductImage(Long productId, ProductImageDTO productImageDTO) throws Exception {
        Product existingProduct=productRepository.findById(productId).orElseThrow(()-> new DataNotFoundException("Product not found"));
        ProductImage newProductImage= ProductImage.builder()
                .product(existingProduct)
                .imageUrl(productImageDTO.getImageUrl())
                .build();
        int size=productImageRepository.findByProductId(productId).size();
        if(size>=5){
            throw new InvalidParameterException("number of images mush be  <5");
        }
        return productImageRepository.save(newProductImage);
    }
}
