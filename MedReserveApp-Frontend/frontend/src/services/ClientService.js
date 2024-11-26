import axios from "axios"

const CLIENT_BASE_REST = "http://localhost:8080/api/client"

class ClientService {
    getAllClients() {
        return axios.get(CLIENT_BASE_REST);
    }
}

export default new ClientService();