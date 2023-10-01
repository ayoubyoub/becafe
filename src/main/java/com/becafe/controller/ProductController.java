package com.becafe.controller;

import com.becafe.dto.ProductDto;
import com.becafe.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productID}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable String productID) {
        Optional<ProductDto> product = productService.getProductById(productID);
        return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@Valid @RequestBody ProductDto product) {
        ProductDto savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{productID}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String productID, @Valid @RequestBody ProductDto updatedProduct) {
        Optional<ProductDto> existingProductOptional = productService.getProductById(productID);

        if (existingProductOptional.isPresent()) {
            ProductDto existingProductEntity = existingProductOptional.get();
            modelMapper.map(updatedProduct, existingProductEntity);
            ProductDto updatedProductEntity = productService.updateProduct(existingProductEntity);
            return ResponseEntity.ok(updatedProductEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{productID}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productID) {
        Optional<ProductDto> product = productService.getProductById(productID);
        if (product.isPresent()) {
            productService.deleteProduct(productID);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
