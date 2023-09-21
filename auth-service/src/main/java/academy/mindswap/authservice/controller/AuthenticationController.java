package academy.mindswap.authservice.controller;

import academy.mindswap.authservice.dto.AuthenticationRequest;
import academy.mindswap.authservice.model.Token;
import academy.mindswap.authservice.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Token> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody AuthenticationRequest authenticationRequest) {
        authenticationService.logout(authenticationRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<Token> refreshToken(AuthenticationRequest authenticationRequest) {

        return ResponseEntity.ok(authenticationService.refreshToken(authenticationRequest));
    }
}
