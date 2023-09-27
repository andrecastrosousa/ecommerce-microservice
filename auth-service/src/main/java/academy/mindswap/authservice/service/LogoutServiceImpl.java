package academy.mindswap.authservice.service;

import academy.mindswap.authservice.model.Token;
import academy.mindswap.authservice.repository.AuthenticationRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LogoutServiceImpl implements LogoutService {
    private final AuthenticationRepository authenticationRepository;

    @Autowired
    LogoutServiceImpl(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        Token token = authenticationRepository.findByToken(jwt)
                .orElse(null);
        if(token != null) {
            authenticationRepository.delete(token);
            SecurityContextHolder.clearContext();
        }
    }
}
