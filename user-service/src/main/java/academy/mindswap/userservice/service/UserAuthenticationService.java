package academy.mindswap.userservice.service;

import academy.mindswap.userservice.dto.LoginRequest;
import academy.mindswap.userservice.dto.RegistrationRequest;
import academy.mindswap.userservice.model.User;

public interface UserAuthenticationService {
    User login(LoginRequest loginRequest);
    User register(RegistrationRequest registrationRequest);
}
