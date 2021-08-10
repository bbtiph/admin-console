package kaz.post.crm.gateway.api.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static kaz.post.crm.gateway.config.ApiProperties.AUTH_API_PREFIX;


@Configuration
public class AuthenticationRouter {

    @Bean
    public RouterFunction<ServerResponse> authRoutes(AuthenticationHandler handler) {
        return RouterFunctions.route(
                RequestPredicates.GET(AUTH_API_PREFIX + "/login")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                handler::login);
    }
}
