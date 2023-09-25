package academy.mindswap.gatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

@Component
public class JwtAuthenticationFilter implements GatewayFilter {
    @Autowired
    private JwtService jwtService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();

        final List<String> apiEndpoints = List.of("/register", "/login");

        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream().noneMatch(uri -> r.getURI().getPath().contains(uri));

        if (isApiSecured.test(request)) {
            if (!request.getHeaders().containsKey("Authorization")) {
                ServerHttpResponse response = (ServerHttpResponse) exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);

                return chain.filter(exchange);
            }

            final String token = request.getHeaders().getOrEmpty("Authorization").get(0);


            if(!jwtService.isTokenValid(token)) {
                ServerHttpResponse response = (ServerHttpResponse) exchange.getResponse();
                response.setStatusCode(HttpStatus.FORBIDDEN);

                return chain.filter(exchange);
            }

            // Claims claims = jwtService.extractClaim(token);
            // exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).build();

        }

        return chain.filter(exchange);
    }
}
