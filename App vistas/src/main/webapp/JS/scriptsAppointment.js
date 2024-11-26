let currentStep = 1;

function nextStep() {    
    document.getElementById(`step${currentStep}`).style.display = "none"; //Hide the current step

    // Shows the next step
    currentStep++;
    document.getElementById(`step${currentStep}`).style.display = "block";

    // Displays date and time availability
    if (currentStep === 3) {
        const doctorSeleccionado = document.getElementById("doctor").value;
        if (doctorSeleccionado) {
            document.getElementById("disponibilidad").style.display = "block";
        } else {
            document.getElementById("disponibilidad").style.display = "none";
        }
    }
}

function prevStep() {    
    document.getElementById(`step${currentStep}`).style.display = "none"; //Hide the current step

    // Shows the previous step
    currentStep--;
    document.getElementById(`step${currentStep}`).style.display = "block";
}

document.getElementById("step1").style.display = "block";