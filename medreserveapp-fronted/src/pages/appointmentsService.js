// src/services/appointmentsService.js

const APPOINTMENTS_API_URL = process.env.REACT_APP_APPOINTMENTS_API_URL;

export const getAppointments = async () => {
  try {
    const response = await fetch(`${APPOINTMENTS_API_URL}`);
    if (!response.ok) {
      throw new Error('Error fetching appointments');
    }
    return await response.json();
  } catch (error) {
    console.error(error);
    throw error;
  }
};
