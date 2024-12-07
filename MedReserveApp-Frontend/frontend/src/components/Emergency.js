import React from 'react';
import { Link } from 'react-router-dom';

const EmergencyService = () => (
  <section className="services" id="emergency">
    <h2>Emergency Service</h2>
    <div className="service-item">
      <img src="emergency.png" alt="Servicios de Emergencia" />
      <h3>Emergency Care</h3>
      <p>We have a team specialized in emergency medical care available 24 hours a day. Our highly trained staff is available to deal with critical situations immediately (054-234591)</p>
    </div>

    <section className="about">
      <h2>How does our Emergency Service work?</h2>
      <p>Our emergency service is available for any type of critical health situation, including accidents, sudden illnesses, and other emergencies that require immediate medical attention.</p>
      <p>We are equipped with state-of-the-art technology and have specialized emergency professionals to offer a quick diagnosis and treatment.</p>
    </section>
  </section>
);

export default EmergencyService;