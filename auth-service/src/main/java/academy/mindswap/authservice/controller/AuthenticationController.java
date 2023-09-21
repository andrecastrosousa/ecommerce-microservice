package academy.mindswap.authservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class AuthenticationController {

    @PostMapping("/authenticate")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("olá");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> register() {
        return ResponseEntity.ok("adeus");
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<String> refreshToken() {
        return ResponseEntity.ok("adeus");
    }
}
