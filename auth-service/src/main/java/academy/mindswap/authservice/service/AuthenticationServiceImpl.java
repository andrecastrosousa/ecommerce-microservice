package academy.mindswap.authservice.service;

import academy.mindswap.authservice.config.JwtService;
import academy.mindswap.authservice.dto.AuthenticationRequest;
import academy.mindswap.authservice.dto.TokenValidationRequest;
import academy.mindswap.authservice.model.Token;
import academy.mindswap.authservice.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationRepository authenticationRepository;
    private final JwtService jwtService;

    @Autowired
    AuthenticationServiceImpl(AuthenticationRepository authenticationRepository, JwtService jwtService) {
        this.authenticationRepository = authenticationRepository;
        this.jwtService = jwtService;
    }

    @Override
    public Token authenticate(AuthenticationRequest authenticationRequest) {
        String jwtToken = jwtService.generateToken(authenticationRequest.username());
        String refreshToken = jwtService.generateRefreshToken(authenticationRequest.username());
        Token token = Token.builder()
                .id(authenticationRequest.username())
                .token(jwtToken)
                .refreshToken(refreshToken).build();

        authenticationRepository.save(token);

        return token;
    }

    @Override
    public void logout(AuthenticationRequest authenticationRequest) {
        authenticationRepository.deleteById(authenticationRequest.username());
    }

    @Override
    public Token refreshToken(AuthenticationRequest authenticationRequest) {
        authenticationRepository.deleteById(authenticationRequest.username());

        String jwtToken = jwtService.generateToken(authenticationRequest.username());
        String refreshToken = jwtService.generateRefreshToken(authenticationRequest.username());
        Token token = Token.builder()
                .id(authenticationRequest.username())
                .token(jwtToken)
                .refreshToken(refreshToken).build();

        authenticationRepository.save(token);
        return token;
    }

    @Override
    public Token verify(TokenValidationRequest tokenValidationRequest) {
        Token token = authenticationRepository.findById(tokenValidationRequest.username()).orElse(null);
        if(token == null || !token.getToken().equals(tokenValidationRequest.token())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not authorized");
        }

        return token;
    }
}
