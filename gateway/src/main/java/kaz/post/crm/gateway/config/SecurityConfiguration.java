package kaz.post.crm.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@EnableWebFluxSecurity
@Slf4j
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(
            ServerHttpSecurity http,
            @Qualifier(value = "jwtAuthenticationWebFilter") AuthenticationWebFilter jwtAuthFilter,
            @Qualifier(value = "ldapAuthenticationWebFilter") AuthenticationWebFilter ldapAuthFilter
    ) {
        return http
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable()
                .authorizeExchange()
                .pathMatchers("/auth/login", "/admin-console-app/crm-server/api/download-excel-report-by-link/**")
                .permitAll()
                .and()
                .authorizeExchange().pathMatchers("/api/hello")
                .access((authentication, object) -> authentication.map(
                        auth -> authorizationDecision(auth, s -> s.equals("ROLE_USERS")))
                ).and()
                .authorizeExchange().pathMatchers("/api/hello-admin")
                .access((authentication, object) -> authentication.map(
                        auth -> authorizationDecision(auth, s -> s.equals("ROLE_ADMINS")))
                )
                .anyExchange().authenticated().and()
                .addFilterAt(ldapAuthFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .addFilterAt(jwtAuthFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

    @Bean
    public ErrorWebExceptionHandler webExceptionHandler() {
        return (exchange, ex) -> {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return Mono.empty();
        };
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private AuthorizationDecision authorizationDecision(Authentication auth, Predicate<? super String> filter) {
        List<String> roleUsers = auth.getAuthorities().stream()
                .map(o -> o.getAuthority())
                .filter(filter)
                .collect(Collectors.toList());
        if (roleUsers.size() > 0) {
            return new AuthorizationDecision(true);
        }
        return new AuthorizationDecision(false);
    }
}
