import Keycloak from 'keycloak-js';

const keycloakConfig = {
  url: 'http://192.168.64.6:8081/admin',
  realm: 'master',
  clientId: 'oauth-client',
};

const keycloak = new Keycloak(keycloakConfig);

export default keycloak;
