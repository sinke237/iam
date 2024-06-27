import React from 'react';
import keycloak from '../components/keycloak';

const Login = () => {
  const handleLogin = () => {
    keycloak.init({ onLoad: 'login-required' })
      .then((authenticated) => {
        if (authenticated) {
          console.log('User is authenticated');
        } else {
          console.error('Authentication failed');
        }
      })
      .catch((error) => {
        console.error('Authentication error', error);
      });
  };

  const handleLogout = () => {
    keycloak.logout();
  };

  return (
    <div>
      <h1>Login Page</h1>
      <button onClick={handleLogin}>Login with Keycloak</button>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
};

export default Login;
