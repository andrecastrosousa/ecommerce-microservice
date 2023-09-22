package academy.mindswap.userservice.service;

import academy.mindswap.userservice.converter.AuthenticationConverter;
import academy.mindswap.userservice.dto.LoginRequest;
import academy.mindswap.userservice.dto.RegistrationRequest;
import academy.mindswap.userservice.dto.TokenRequest;
import academy.mindswap.userservice.dto.TokenResponse;
import academy.mindswap.userservice.model.User;
import academy.mindswap.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    private final UserRepository userRepository;
    private final WebClient webClient;
    private final AuthenticationConverter authenticationConverter;

    @Autowired
    UserAuthenticationServiceImpl(UserRepository userRepository, WebClient webClient, AuthenticationConverter authenticationConverter) {
        this.userRepository = userRepository;
        this.webClient = webClient;
        this.authenticationConverter = authenticationConverter;
    }

    @Override
    public User login(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByEmailAndPassword(loginRequest.username(), loginRequest.password());

        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Credentials");
        }

        Mono<TokenResponse> tokenResponse = webClient
                .post()
                .uri("/authenticate")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(new TokenRequest(loginRequest.username()), TokenRequest.class)
                .retrieve()
                .bodyToMono(TokenResponse.class);

        tokenResponse.subscribe();

        return null;
    }

    @Override
    public User register(RegistrationRequest registrationRequest) {
        User user = authenticationConverter.toEntityFromRegistration(registrationRequest);
        // TODO: Endpoint to add balance instead of define this value, this will help to test buy an order
        user.setBalance(1000);
        userRepository.save(user);

        Mono<TokenResponse> tokenResponse = webClient
                .post()
                .uri("/authenticate")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(new TokenRequest(user.getEmail()), TokenRequest.class)
                .retrieve()
                .bodyToMono(TokenResponse.class);

        tokenResponse.subscribe();

        return user;
    }
}
