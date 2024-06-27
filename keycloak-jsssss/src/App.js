import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import FailedAuth from './components/FailedAuth';
import SinkeResources from './components/SinkeResources';
import Login from './components/Login';

const App = () => {
  return (
    <Router>
      <div>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/failed-auth" element={<FailedAuth />} />
          <Route path="/sinke-resources" element={<SinkeResources />} />
          {/* Additional routes for other components */}
        </Routes>
      </div>
    </Router>
  );
};

const Home = () => {
  return (
    <div>
      <h1>Welcome to the Home Page</h1>
      <p>Please <Login /> to access protected resources.</p>
    </div>
  );
};

export default App;
