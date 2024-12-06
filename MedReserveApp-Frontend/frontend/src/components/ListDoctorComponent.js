import React, { useEffect, useState } from 'react';
import DoctorService from '../services/DoctorService.js';  // Asegúrate de tener un servicio para obtener los doctores
import '../Styles/stuleIndex.css';
import '../Styles/styleTable.css';

export const ListDoctorsComponent = () => {
   const [doctors, setDoctors] = useState([]);

   useEffect(() => {
      // Llamada a la API para obtener los doctores
      DoctorService.getAllDoctors()
         .then(response => {
            setDoctors(response.data);  // Guarda los datos de los doctores en el estado
            console.log(response.data);  // Verifica la respuesta de la API
         })
         .catch(error => {
            console.log(error);  // Maneja cualquier error
         });
   }, []);

   return (
      <div>
            <h1 className='center'>Doctors List</h1>

         <main>
            <table id="dataTable" className="styled-table">
               <thead>
                  <tr>
                     <th>Doctor Name</th>
                     <th>Specialty</th>
                  </tr>
               </thead>
               <tbody>
                  {doctors.map(doctor => (
                     <tr key={doctor.id}>
                        <td>{doctor.doctorName}</td>
                        <td>{doctor.specialty}</td>
                     </tr>
                  ))}
               </tbody>
            </table>
            <div id="pagination" className="pagination"></div>
         </main>
      </div>
   );
}

export default ListDoctorsComponent;
