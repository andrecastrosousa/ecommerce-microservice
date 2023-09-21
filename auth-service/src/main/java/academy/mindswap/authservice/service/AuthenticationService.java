package academy.mindswap.authservice.service;

import academy.mindswap.authservice.dto.AuthenticationRequest;
import academy.mindswap.authservice.model.Token;

import java.io.IOException;

public interface AuthenticationService {
    Token authenticate(AuthenticationRequest authenticationRequest);
    void logout(AuthenticationRequest authenticationRequest);
    Token refreshToken(AuthenticationRequest authenticationRequest);
}
