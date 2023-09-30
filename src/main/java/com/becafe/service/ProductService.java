package com.becafe.service;

import com.becafe.dto.ProductDto;
import com.becafe.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDto> getAllProducts();

    Optional<ProductDto> getProductById(Long id);

    ProductDto saveProduct(ProductDto productDto);

    void deleteProduct(Long id);
}
