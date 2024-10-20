import logo from './logo.svg';
import './App.css';
import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import AppointmentsPage from './pages/AppointmentsPage';
import ClientsPage from './pages/ClientsPage';
import DoctorsPage from './pages/DoctorsPage';

function App() {
  return (
    <Router>
      <Switch>
        <Route path="/appointments" component={AppointmentsPage} />
        <Route path="/clients" component={ClientsPage} />
        <Route path="/doctors" component={DoctorsPage} />
      </Switch>
    </Router>
  );
}

export default App;
