package africa.semicolon.lumExpress.services.userService;

import africa.semicolon.lumExpress.data.dtos.request.LoginRequest;
import africa.semicolon.lumExpress.data.dtos.response.LoginResponse;
import africa.semicolon.lumExpress.data.models.LumExpressUser;
import africa.semicolon.lumExpress.exceptions.UserNotFoundException;

import java.util.Optional;

public interface iUserService {
    LoginResponse login(LoginRequest request);
    LumExpressUser getUerByUsername(String email) throws UserNotFoundException;
}
