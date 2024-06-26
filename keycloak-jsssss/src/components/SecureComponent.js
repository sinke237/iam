import React, { useEffect, useState } from 'react';
import keycloak from '../keycloak'; 

const SecureComponent = () => {
    const [authenticated, setAuthenticated] = useState(false);

    useEffect(() => {
        keycloak.init({ onLoad: 'login-required' })
            .then(authenticated => {
                setAuthenticated(authenticated);
            })
            .catch(err => {
                console.error('Authentication error', err);
            });
    }, []);

    return (
        <div>
            {authenticated ? (
                <p>User is authenticated</p>
            ) : (
                <p>User is not authenticated</p>
            )}
        </div>
    );
};

export default SecureComponent;
