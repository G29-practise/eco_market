package org.example.eco_v2.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.eco_v2.common.App;
import org.example.eco_v2.product.dto.ProductCreateDto;
import org.example.eco_v2.product.dto.ProductResponseDto;
import org.example.eco_v2.product.dto.ProductUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(App.BASE_PATH + ProductController.BATH_URL)
@RequiredArgsConstructor
public class ProductController {

    public static final String BATH_URL = "/product";

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDto> createProduct(@ModelAttribute @Valid ProductCreateDto productCreateDto) throws IOException {
        ProductResponseDto productResponseDto = productService.create(productCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productResponseDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<ProductResponseDto>>getAllProduct(@RequestParam(required = false) String predicate, Pageable pageable){
        Page<ProductResponseDto> productResponseDtoPage = productService.getAll(predicate, pageable);
        return ResponseEntity.ok(productResponseDtoPage);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ProductResponseDto>getProduct(@PathVariable UUID id){
        ProductResponseDto productResponseDto = productService.get(id);
        return ResponseEntity.ok(productResponseDto);
    }

    @GetMapping("/getProductTitle/{productTitle}")
    public ResponseEntity<ProductResponseDto>getProduct(@PathVariable String productTitle){
        ProductResponseDto productResponseDto = productService.getByTitle(productTitle);
        return ResponseEntity.ok(productResponseDto);
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<ProductResponseDto>updateProduct(@PathVariable UUID id, @RequestBody ProductUpdateDto productUpdateDto){
        ProductResponseDto productResponseDto = productService.internalUpdate(id, productUpdateDto);
        return ResponseEntity.ok(productResponseDto);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable UUID id){
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
