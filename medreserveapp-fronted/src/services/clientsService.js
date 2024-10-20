// src/services/clientsService.js

const CLIENTS_API_URL = process.env.REACT_APP_CLIENTS_API_URL;

export const getClients = async () => {
  try {
    const response = await fetch(`${CLIENTS_API_URL}`);
    if (!response.ok) {
      throw new Error('Error fetching clients');
    }
    return await response.json();
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const createClient = async (clientData) => {
  try {
    const response = await fetch(`${CLIENTS_API_URL}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(clientData),
    });
    if (!response.ok) {
      throw new Error('Error creating client');
    }
    return await response.json();
  } catch (error) {
    console.error(error);
    throw error;
  }
};
