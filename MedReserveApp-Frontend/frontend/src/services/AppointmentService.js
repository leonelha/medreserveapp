import axios from 'axios';

const APPOINTMENT_BASE_REST = "http://localhost:8080/api/appointment";
class AppointmentService {

    getAllAppointments() {
        return axios.get(APPOINTMENT_BASE_REST);
    }

    getAppointmentsByClientId(clientId) {
        return axios.get(`${APPOINTMENT_BASE_REST}?clientId=${clientId}`);
    }
}

export default new AppointmentService();