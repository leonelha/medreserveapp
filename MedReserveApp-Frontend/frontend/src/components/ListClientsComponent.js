import React, { useEffect, useState } from 'react';
import ClientService from '../services/ClientService';
import '../Styles/styleTable.css';

const ListClientsComponent = () => {
  const [clients, setClients] = useState([]);

  useEffect(() => {
    ClientService.getAllClients()
      .then(response => {
        setClients(response.data);
      })
      .catch(error => {
        console.log('Error fetching clients:', error);
      });
  }, []);

  return (
    <div>
      <h1 className='center'>Clients List</h1>
      <table className="styled-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Client</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Address</th>
          </tr>
        </thead>
        <tbody>
          {clients.map(client => (
            <tr key={client.id}>
              <td>{client.id}</td>
              <td>{client.firstName} {client.lastName}</td>
              <td>{client.email}</td>
              <td>{client.phone}</td>
              <td>{client.address}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ListClientsComponent;