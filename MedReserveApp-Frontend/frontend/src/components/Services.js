import React from 'react';
import { Link } from 'react-router-dom';

const Services = () => (
  <section className="services" id="services">
    <h2>Our Services</h2>
    <div className="service-grid">
      <Link to="/Patient" className="service-item">
        <img src="consul.png" alt="Consulta Médica General" />
        <h3>Medical Consultation</h3>
        <p className="description">Care for all your medical needs</p>
      </Link>

      <Link to="/Specialties" className="service-item">
        <img src="especialitation.png" alt="Especialidades Médicas" />
        <h3>Medical Specialties</h3>
        <p className="description">We offer specialized services in various medical areas</p>
      </Link>
      
      <Link to="/Emergency" className="service-item">
        <img src="ambulation.png" alt="Servicios de Emergencia" />
        <h3>Emergency Service</h3>
        <p className="description">Immediate and specialized attention in emergency situations</p>
      </Link>
    </div>
  </section>
);

export default Services;