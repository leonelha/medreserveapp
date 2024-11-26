import React from 'react';
import { Link } from 'react-router-dom';
import '../Styles/stuleIndex.css'

const Banner = () => (
  <section className="banner" id="home">
    <div className="banner-content">
      <img src="Logo.png" alt="Logo de la Clínica" className="logoB" />
      <h2>Welcome to UTP Clinic</h2>
      <p>Your health is our priority. Book your medical appointment easily</p>
      <div className="banner-buttons">
        <Link to="/Patient" className="btn">I am patient</Link>
        <Link to="/Doctor" className="btn">I am Doctor</Link>
      </div>
    </div>
  </section>
);

export default Banner;