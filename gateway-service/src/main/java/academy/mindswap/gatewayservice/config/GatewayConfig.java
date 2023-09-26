package academy.mindswap.gatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("USER-SERVICE", r -> r
                        .path("/api/users/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost/8090/api/users/**"))
                .route("ORDER-SERVICE", r -> r
                        .path("/api/orders/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost/8090/api/orders/**"))
                .route("CATALOG-SERVICE", r -> r
                        .path("/api/items/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost/8090/api/items/**"))
                .route("AUTH-SERVICE", r -> r
                        .path("/api/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost/8090/api/users/**"))
                .build();
    }
}
