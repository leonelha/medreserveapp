// src/services/doctorsService.js

const DOCTORS_API_URL = process.env.REACT_APP_DOCTORS_API_URL;

export const getDoctors = async () => {
  try {
    const response = await fetch(`${DOCTORS_API_URL}`);
    if (!response.ok) {
      throw new Error('Error fetching doctors');
    }
    return await response.json();
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const createDoctor = async (doctorData) => {
  try {
    const response = await fetch(`${DOCTORS_API_URL}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(doctorData),
    });
    if (!response.ok) {
      throw new Error('Error creating doctor');
    }
    return await response.json();
  } catch (error) {
    console.error(error);
    throw error;
  }
};
