package com.freelancer.billing.service;

import com.freelancer.billing.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findProductByProductId(String productId);

    Optional<Product> findProductByBarcode(String barcode);

    List<Product> findProductsByProductNameLike(String name);

    List<Product> findProductsByCategoryId(String id);

    Product saveProduct(Product product);

    Product updateProduct(Product product);

    Product deleteProduct(Product product);
}
