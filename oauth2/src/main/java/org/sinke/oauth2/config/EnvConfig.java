package org.sinke.oauth2.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {

    @jakarta.annotation.PostConstruct
    public void init() {
        
        // Load .env file
        Dotenv dotenv = Dotenv.configure().directory("/Users/studac/sinke/iam/oauth2/.env").load();

        String authServerUrl = dotenv.get("KEYCLOAK_AUTH_SERVER_URL");
        String realm = dotenv.get("KEYCLOAK_REALM");
        String clientId = dotenv.get("KEYCLOAK_CLIENT_ID");
        String clientSecret = dotenv.get("KEYCLOAK_CLIENT_SECRET");
        String sslRequired = dotenv.get("KEYCLOAK_SSL_REQUIRED");
        String issuerUri = dotenv.get("KEYCLOAK_ISSUER_URI");

        // Optionally, set environment variables as system properties
        System.setProperty("KEYCLOAK_AUTH_SERVER_URL", authServerUrl);
        System.setProperty("KEYCLOAK_REALM", realm);
        System.setProperty("KEYCLOAK_CLIENT_ID", clientId);
        System.setProperty("KEYCLOAK_CLIENT_SECRET", clientSecret);
        System.setProperty("KEYCLOAK_SSL_REQUIRED", sslRequired);
        System.setProperty("KEYCLOAK_ISSUER_URI", issuerUri);
    }
}
