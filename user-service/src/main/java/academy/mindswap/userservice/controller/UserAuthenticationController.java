package academy.mindswap.userservice.controller;

import academy.mindswap.userservice.dto.LoginRequest;
import academy.mindswap.userservice.dto.RegistrationRequest;
import academy.mindswap.userservice.model.User;
import academy.mindswap.userservice.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class UserAuthenticationController {
    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    UserAuthenticationController(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest loginRequest) {
        return userAuthenticationService.login(loginRequest);
    }

    public User register(@RequestBody RegistrationRequest registrationRequest) {
        return userAuthenticationService.register(registrationRequest);
    }

}
