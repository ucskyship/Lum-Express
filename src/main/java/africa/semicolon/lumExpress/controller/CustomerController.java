package africa.semicolon.lumExpress.controller;

import africa.semicolon.lumExpress.data.dtos.request.AddProductRequest;
import africa.semicolon.lumExpress.data.dtos.request.CustomerRegistrationRequest;
import africa.semicolon.lumExpress.data.models.Category;
import africa.semicolon.lumExpress.exceptions.LumExpressException;
import africa.semicolon.lumExpress.services.customerService.CustomerServiceImpl;
import africa.semicolon.lumExpress.services.productService.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class CustomerController {
    private final CustomerServiceImpl customerService;
    private final ProductServiceImpl productService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid CustomerRegistrationRequest registrationRequest) throws LumExpressException {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.register(registrationRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PostMapping(path = "/addProduct", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addProduct(@RequestParam String name,
                                        @RequestParam Double price,
                                        @RequestParam Category category,
                                        @RequestParam Integer quantity,
                                        @RequestPart MultipartFile multipartFile) throws IOException {
        AddProductRequest addProductRequest = AddProductRequest
                .builder()
                .productName(name)
                .productCategory(category.name())
                .quantity(quantity)
                .price(BigDecimal.valueOf(price))
                .imageUrl(multipartFile)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(addProductRequest));
    }
}
