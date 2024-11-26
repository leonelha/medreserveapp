import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Header from './components/Header';
import Banner from './components/Banner';
import Services from './components/Services';
import Footer from './components/Footer';
import ListClientsComponent from './components/ListClientsComponent';
import ListAppointmentComponent from './components/ListAppointmentComponent';
import Specialties from './components/Specialties';

const App = () => {
  return (
    <Router>
      <Header />
      <Banner />
      <Routes>
        <Route path="/" element={<Services />} />
        <Route path="/Patient" element={<ListClientsComponent />} />
        <Route path="/Doctor" element={<h1>Doctor Page</h1>} />
        <Route path="/specialties" element={<Specialties />} />
        <Route path="/Emergency" element={<h1>Emergency Page</h1>} />
        <Route path="/MedicalAppoinment"  element={<ListAppointmentComponent />} />
      </Routes>
      <Footer />
    </Router>
  );
};

export default App;