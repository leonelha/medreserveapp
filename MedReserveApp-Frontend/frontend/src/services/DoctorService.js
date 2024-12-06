import axios from "axios"

const DOCTOR_BASE_REST = "http://localhost:8080/api/doctor"

class DoctorService {
    getAllDoctors() {
        return axios.get(DOCTOR_BASE_REST);
    }
}

export default new DoctorService();