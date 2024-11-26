import React, { useEffect, useState } from 'react';
import AppointmentService from '../services/AppointmentService.js';
import '../Styles/stuleIndex.css';
import '../Styles/styleTable.css';

export const ListAppointmentsComponent = () => {
   const [appointments, setAppointments] = useState([]);

   useEffect(() => {
      AppointmentService.getAllAppointments()
         .then(response => {
            setAppointments(response.data);
            console.log(response.data);
         })
         .catch(error => {
            console.log(error);
         });
   }, []);

   return (
      <div>
         <main>
            <table id="dataTable" className="styled-table">
               <thead>
                  <tr>
                     <th>ID</th>
                     <th>Appointment Date</th>
                     <th>Notes</th>
                     <th>Client ID</th>
                  </tr>
               </thead>
               <tbody>
                  {appointments.map(appointment => (
                     <tr key={appointment.id}>
                        <td>{appointment.id}</td>
                        <td>{appointment.appointmentDate}</td>
                        <td>{appointment.notes}</td>
                        <td>{appointment.clientId}</td>
                     </tr>
                  ))}
               </tbody>
            </table>
            <div id="pagination" className="pagination"></div>
         </main>
      </div>
   );
}

export default ListAppointmentsComponent;