package africa.semicolon.lumExpress.controller;

import africa.semicolon.lumExpress.data.dtos.request.CustomerRegistrationRequest;
import africa.semicolon.lumExpress.services.customerService.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerServiceImpl customerService;

    @PostMapping()
    public ResponseEntity<?> register(@RequestBody @Valid CustomerRegistrationRequest registrationRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.register(registrationRequest));
    }
}
