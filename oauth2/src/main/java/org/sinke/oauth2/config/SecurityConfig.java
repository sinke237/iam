package org.sinke.oauth2.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(
            ClientRegistration.withRegistrationId("keycloak")
                .clientId("oauth2")
                .clientSecret("fEGP0YwCQWSSMswswHACLnaV1kORXKYK")
                .clientName("Keycloak")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .scope("openid", "profile", "email")
                .authorizationUri("http://192.168.64.6:8081/auth/realms/oauth2/protocol/openid-connect/auth")
                .tokenUri("http://192.168.64.6:8081/auth/realms/oauth2/protocol/openid-connect/token")
                .userInfoUri("http://192.168.64.6:8081/auth/realms/oauth2/protocol/openid-connect/userinfo")
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .jwkSetUri("http://192.168.64.6:8081/auth/realms/oauth2/protocol/openid-connect/certs")
                .issuerUri("http://192.168.64.6:8081/auth/realms/oauth2")
                .build()
        );
    }
}


