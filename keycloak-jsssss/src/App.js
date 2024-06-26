// App.js

import React from 'react';
import SecureComponent from './components/SecureComponent';

function App() {
    return (
        <div className="App">
            <h1>React App with Keycloak Authentication</h1>
            <SecureComponent />
        </div>
    );
}

export default App;
