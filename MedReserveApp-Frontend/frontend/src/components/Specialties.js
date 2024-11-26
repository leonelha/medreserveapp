import React from 'react';
import '../Styles/stuleIndex.css';

const Specialties = () => {
  return (
    <div>
      <section className="services">
        <h2>Our Medical Specialties</h2>
        <div className="service-grid">
          <div className="service-item">
            <img src="cardiologia.png" alt="Cardiología" />
            <h3>Cardiology</h3>
            <p>Specialized care for the diagnosis and treatment of diseases of the heart and circulatory system</p>
          </div>
          <div className="service-item">
            <img src="dermatologist.png" alt="Dermatología" />
            <h3>Dermatologist</h3>
            <p>Diagnosis and treatment of skin, hair and nail diseases</p>
          </div>
          <div className="service-item">
            <img src="pediatr.png" alt="Pediatría" />
            <h3>Pediatrics</h3>
            <p>Comprehensive medical care for babies, children, and adolescents</p>
          </div>
          <div className="service-item">
            <img src="neurology.png" alt="Neurología" />
            <h3>Neurology</h3>
            <p>Specialists in the treatment of diseases of the central and peripheral nervous system</p>
          </div>
          <div className="service-item">
            <img src="traumatology.png" alt="Ginecología" />
            <h3>Traumatology</h3>
            <p>Diagnosis and treatment of injuries, fractures, and sprains</p>
          </div>
        </div>
      </section>
    </div>
  );
};

export default Specialties;