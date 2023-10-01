package com.becafe.service;

import com.becafe.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDto> getAllProducts();

    Optional<ProductDto> getProductById(String productID);

    ProductDto saveProduct(ProductDto productDto);

    void deleteProduct(String productID);
}
