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
                        .uri("http://localhost/8083/api/users/**"))
                .route("USER-SERVICE-AUTH", r -> r
                        .path("/login", "/register")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost/8083/**"))
                .route("ORDER-SERVICE", r -> r
                        .path("/api/orders/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost/8080/api/orders/**"))
                .route("CATALOG-SERVICE", r -> r
                        .path("/api/items/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost/8081/api/items/**"))
                .route("AUTH-SERVICE-LOGOUT", r -> r
                        .path("/logout")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost/8082/logout"))
                .route("AUTH-SERVICE", r -> r
                        .path("/authenticate", "/token")
                        .filters(f -> f.filter(filter))
                        .uri("lb://**"))
                .build();
    }
}
