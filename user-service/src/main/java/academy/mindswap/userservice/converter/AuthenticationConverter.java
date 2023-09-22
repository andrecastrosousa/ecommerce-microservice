package academy.mindswap.userservice.converter;

import academy.mindswap.userservice.dto.RegistrationRequest;
import academy.mindswap.userservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationConverter {
    public User toEntityFromRegistration(RegistrationRequest registrationRequest) {
        return User.builder()
                .email(registrationRequest.getEmail())
                .password(registrationRequest.getPassword())
                .name(registrationRequest.getName())
                .build();
    }
}
