package academy.mindswap.authservice.service;

import academy.mindswap.authservice.config.JwtService;
import academy.mindswap.authservice.dto.AuthenticationRequest;
import academy.mindswap.authservice.model.Token;
import academy.mindswap.authservice.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    }

    @Override
    public Token refreshToken(AuthenticationRequest authenticationRequest) {
        return null;
    }
}
