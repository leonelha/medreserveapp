import React from 'react';
import { Link } from 'react-router-dom';
import '../Styles/stuleIndex.css'

const Header = () => (
  <header>
    <div className="logo-container">
      <a href="index.html">
        <img src="Logo.png" alt="Logo de la Clínica" className="logo" />
      </a>
      <h1>UTP Clinic</h1>
    </div>
    <nav>
      <ul>
        <li><Link to="#services">Services</Link></li>
        <li><Link to="Specialties">Specialties</Link></li>
        <li><Link to="Emergency">Emergency</Link></li>
        <li><Link to="MedicalAppoinment">Appointment</Link></li>
      </ul>
    </nav>
  </header>
);

export default Header;