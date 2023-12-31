package academy.mindswap.authservice.service;

import academy.mindswap.authservice.dto.AuthenticationRequest;
import academy.mindswap.authservice.dto.TokenValidationRequest;
import academy.mindswap.authservice.model.Token;

public interface AuthenticationService {
    Token authenticate(AuthenticationRequest authenticationRequest);
    void logout(AuthenticationRequest authenticationRequest);
    Token refreshToken(AuthenticationRequest authenticationRequest);

    Token verify(TokenValidationRequest tokenValidationRequest);
}
