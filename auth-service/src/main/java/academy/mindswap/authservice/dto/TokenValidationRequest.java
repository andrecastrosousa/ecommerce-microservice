package academy.mindswap.authservice.dto;

public record TokenValidationRequest(String username, String token, String refreshToken) {
}
