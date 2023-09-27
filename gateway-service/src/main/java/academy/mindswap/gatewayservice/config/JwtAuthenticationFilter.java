package academy.mindswap.gatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

@Component
public class JwtAuthenticationFilter implements GatewayFilter {
    private final WebClient webClient;

    @Autowired
    JwtAuthenticationFilter(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        final String authHeader = request.getHeaders().getOrEmpty("Authorization").get(0);
        final String jwt = authHeader.substring(7);

        return webClient.get()
                .uri("/token")
                .header("Authorization", jwt)
                .exchangeToMono(responseHandler -> {
                    if(!responseHandler.statusCode().is2xxSuccessful()) {
                        ServerHttpResponse response = exchange.getResponse();
                        response.setStatusCode(HttpStatus.FORBIDDEN);

                        return response.setComplete();
                    }

                    return chain.filter(exchange);
                });

    }
}
