package academy.mindswap.authservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@RedisHash("Token")
@Builder
public class Token implements Serializable {
    private String id;
    private String token;
    private String refreshToken;
}
