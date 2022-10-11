package africa.semicolon.lumExpress.services.userService;

import africa.semicolon.lumExpress.data.dtos.request.LoginRequest;
import africa.semicolon.lumExpress.data.dtos.response.LoginResponse;

public interface iUserService {
    LoginResponse login(LoginRequest request);
}
