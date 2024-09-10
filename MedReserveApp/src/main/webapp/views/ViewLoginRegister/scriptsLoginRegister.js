document.addEventListener('DOMContentLoaded', function() {    //Wait for the DOM content to load
    // Login and Registration Patient
    const showPatientRegister = document.getElementById('showPatientRegister');
    const showPatientLogin = document.getElementById('showPatientLogin');

    // Check if elements exist in the DOM
    if (showPatientRegister && showPatientLogin) {
        showPatientRegister.addEventListener('click', function(e) {
            e.preventDefault();
            document.getElementById('loginPatientForm').classList.add('hidden');
            document.getElementById('registerPatientForm').classList.remove('hidden');
        });

        showPatientLogin.addEventListener('click', function(e) {
            e.preventDefault();
            document.getElementById('registerPatientForm').classList.add('hidden');
            document.getElementById('loginPatientForm').classList.remove('hidden');
        });
    }

    // Login and Registration Doctor
    const showMedicRegister = document.getElementById('showMedicRegister');
    const showMedicoLogin = document.getElementById('showMedicoLogin');

    //Check if elements exist in the DOM
    if (showMedicRegister && showMedicoLogin) {
        showMedicRegister.addEventListener('click', function(e) {
            e.preventDefault();
            document.getElementById('loginMedicoForm').classList.add('hidden');
            document.getElementById('registerMedicoForm').classList.remove('hidden');
        });

        showMedicoLogin.addEventListener('click', function(e) {
            e.preventDefault();
            document.getElementById('registerMedicoForm').classList.add('hidden');
            document.getElementById('loginMedicoForm').classList.remove('hidden');
        });
    }

    // Simulation register and login "patient and doctor"
    const loginPatient = document.getElementById('loginPatient');
    const registerPatient = document.getElementById('registerPatient');
    const loginMedico = document.getElementById('loginMedico');
    const registerMedico = document.getElementById('registerMedico');

    //Patient
    if (loginPatient) {
        loginPatient.addEventListener('submit', function(e) {
            e.preventDefault();
            alert('Bienvenido, paciente');
        });
    }

    if (registerPatient) {
        registerPatient.addEventListener('submit', function(e) {
            e.preventDefault();
            alert('Registro completado como paciente. Ahora puedes iniciar sesión.');
            document.getElementById('registerPatientForm').classList.add('hidden');
            document.getElementById('loginPatientForm').classList.remove('hidden');
        });
    }

    //Doctor
    if (loginMedico) {
        loginMedico.addEventListener('submit', function(e) {
            e.preventDefault();
            alert('Welcome, doctor.');
        });
    }

    if (registerMedico) {
        registerMedico.addEventListener('submit', function(e) {
            e.preventDefault();
            alert('Registro completado como doctor. Ahora puedes iniciar sesión.');
            document.getElementById('registerMedicoForm').classList.add('hidden');
            document.getElementById('loginMedicoForm').classList.remove('hidden');
        });
    }
});