package com.freelancer.billing.service.impl;

import com.freelancer.billing.domain.Product;
import com.freelancer.billing.repository.ProductRepository;
import com.freelancer.billing.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductByProductId(String productId){
        LOGGER.info("looking for product by its product id");

        Optional<Product> product = productRepository.findByProductId(productId);

        if(!product.isPresent())
            throw new IllegalArgumentException("Invalid productId");

        return product;
    }

    @Override
    public Optional<Product> findProductByBarcode(String barcode){
        LOGGER.info("looking for product by its barcode");

        Optional<Product> product = productRepository.findByBarcode(barcode);

        return product;
    }


    @Override
    public List<Product> findProductsByProductNameLike(String name) {
        LOGGER.info("looking for products by name");

        return productRepository.findByNameLike(name);
    }

    @Override
    public List<Product> findProductsByCategoryId(String id){

        return productRepository.findByCategoryId(id);
    }

    @Override
    public Product saveProduct(Product product) {
        LOGGER.info("Saving products");
        productRepository.save(product);

        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(Product product) {
        LOGGER.info("Deleting product");
        productRepository.delete(product);

        return product;
    }
}
