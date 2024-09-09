document.getElementById('appointmentForm').addEventListener('submit', function(e) {
    e.preventDefault();

    // Obtener los valores del formulario
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const phone = document.getElementById('phone').value;
    const date = document.getElementById('date').value;
    const time = document.getElementById('time').value;
    const doctor = document.getElementById('doctor').value;

    // Validación básica
    if (name && email && phone && date && time && doctor) {
        // Mostrar mensaje de confirmación
        document.getElementById('confirmation').classList.remove('hidden');
        // Limpiar el formulario
        document.getElementById('appointmentForm').reset();
    } else {
        alert('Por favor, complete todos los campos.');
    }
});