package org.sinke.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.server.WebSessionServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .authorizeExchange(exchanges ->
                exchanges
                    .pathMatchers("/", "/home").permitAll()
                    .pathMatchers("/sinke-resources").authenticated()
                    .anyExchange().authenticated()
            )
            .oauth2Login(oauth2Login ->
                oauth2Login
                    .authenticationSuccessHandler((webFilterExchange, authentication) ->
                        Mono.fromRunnable(() ->
                            webFilterExchange.getExchange().getResponse()
                                .getHeaders().setLocation(URI.create("/sinke-resources"))
                        ).then()
                    )
                    .authenticationFailureHandler((webFilterExchange, exception) ->
                        Mono.fromRunnable(() ->
                            webFilterExchange.getExchange().getResponse()
                                .getHeaders().setLocation(URI.create("/failed-auth"))
                        ).then()
                    )
            );
            
        return http.build();
    }

    @Bean
    public ReactiveClientRegistrationRepository reactiveClientRegistrationRepository() {
        // Configure Keycloak Client Registration
        ClientRegistration registration = ClientRegistration.withRegistrationId("keycloak")
            .clientId("${KEYCLOAK_CLIENT_ID}")
            .clientSecret("${KEYCLOAK_CLIENT_SECRET}")
            .clientName("Keycloak")
            .scope("openid", "profile", "email")
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
            .authorizationUri("${KEYCLOAK_AUTH_SERVER_URL}/realms/${KEYCLOAK_REALM}/protocol/openid-connect/auth")
            .tokenUri("${KEYCLOAK_AUTH_SERVER_URL}/realms/${KEYCLOAK_REALM}/protocol/openid-connect/token")
            .userInfoUri("${KEYCLOAK_AUTH_SERVER_URL}/realms/${KEYCLOAK_REALM}/protocol/openid-connect/userinfo")
            .jwkSetUri("${KEYCLOAK_AUTH_SERVER_URL}/realms/${KEYCLOAK_REALM}/protocol/openid-connect/certs")
            .build();

        return new InMemoryReactiveClientRegistrationRepository(registration);
    }

    @Bean
    public ServerOAuth2AuthorizedClientRepository authorizedClientRepository() {
        return new WebSessionServerOAuth2AuthorizedClientRepository();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
