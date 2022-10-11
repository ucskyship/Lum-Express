package africa.semicolon.lumExpress.services.userService;

import africa.semicolon.lumExpress.data.dtos.request.LoginRequest;
import africa.semicolon.lumExpress.data.dtos.response.LoginResponse;
import africa.semicolon.lumExpress.data.models.LumExpressUser;
import africa.semicolon.lumExpress.data.repositories.AdminRepository;
import africa.semicolon.lumExpress.data.repositories.CustomerRepository;
import africa.semicolon.lumExpress.data.repositories.VendorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements iUserService{
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;
    @Override
    public LoginResponse login(LoginRequest request) {
        var foundCustomer = customerRepository.findByEmail(request.getEmail());
        if (foundCustomer.isPresent() && foundCustomer.get().getPassword().equals(request.getPassword()))
            return buildSuccessfulLoginResponse(foundCustomer.get());
        var foundAdmin = adminRepository.findByEmail(request.getEmail());
        if (foundAdmin.isPresent() && foundAdmin.get().getPassword().equals(request.getPassword()))
            return buildSuccessfulLoginResponse(foundAdmin.get());
        var foundVendor = vendorRepository.findByEmail(request.getEmail());
        if (foundVendor.isPresent() && foundVendor.get().getPassword().equals(request.getPassword()))
            return buildSuccessfulLoginResponse(foundVendor.get());

        return LoginResponse.builder()
                .code(400)
                .message("Login failed. Invalid credentials")
                .build();
    }

    private LoginResponse buildSuccessfulLoginResponse(LumExpressUser lumExpressUser) {
        return LoginResponse.builder()
                .message("user logged in successfully")
                .code(200)
                .build();
    }
}
