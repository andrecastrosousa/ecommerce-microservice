package academy.mindswap.userservice.dto;

public record OrderProcessedPayload(Long id, double total, String email) {
}
