import React, { useState } from 'react';
import '../Styles/stuleIndex.css';
import '../Styles/stylesAppointment.css';
import '../Styles/stuleIndex.css';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    // Aquí manejarías el submit para login
    console.log('Email:', email);
    console.log('Password:', password);
    // Puedes agregar la lógica para autenticar al paciente con una API o servicio.
  };

  return (
    <div className="container">
      {/* Logo */}
      <div className="logo-container">
        <img src="Logo.png" alt="Logo del Sistema" className="logo" />
      </div>

      {/* Formulario de Login */}
      <div className="form-container" id="loginPatientForm">
        <h2>Sign in</h2>
        <form id="loginPatient" onSubmit={handleSubmit}>
          <label htmlFor="login-patient-email">Email:</label>
          <input
            type="email"
            id="login-patient-email"
            name="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />

          <label htmlFor="login-patient-password">Password:</label>
          <input
            type="password"
            id="login-patient-password"
            name="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />

          <button type="submit">Sign in</button>
          <p>
            Don't have an account?{' '}
            <a href="#" id="showPatientRegister">Sign up here</a>
          </p>
        </form>
      </div>
    </div>
  );
};

export default Login;