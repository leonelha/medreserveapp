import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Header from './components/Header';
import Banner from './components/Banner';
import Services from './components/Services';
import Footer from './components/Footer';
import ListClientsComponent from './components/ListClientsComponent';
import ListAppointmentComponent from './components/ListAppointmentComponent';
import ListDoctorComponent from "./components/ListDoctorComponent"
import Specialties from './components/Specialties';
import Emergency from './components/Emergency';

const App = () =>
   {
  return (
    <Router>
      <Header />
      <Banner />
      <Routes>
        <Route path="/Services" element={<Services />} />
        <Route path="/Patient" element={<ListClientsComponent />} />
        <Route path="/Doctor" element={<ListDoctorComponent />}/>
        <Route path="/specialties" element={<Specialties />} />
        <Route path="/Emergency" element={<Emergency />} />
        <Route path="/MedicalAppoinment"  element={<ListAppointmentComponent />} />
      </Routes>
      <Footer />
    </Router>
  );
};

export default App;