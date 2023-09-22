package academy.mindswap.userservice.dto;

public record TokenResponse(String username, String token, String refreshToken) {
}
