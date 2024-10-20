
import axios from 'axios';

const APPOINTMENTS_API_URL = process.env.REACT_APP_APPOINTMENTS_API_URL;

export const getAppointments = async () => {
  try {
    const response = await axios.get(`${APPOINTMENTS_API_URL}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching appointments:', error);
    throw error;
  }
};
