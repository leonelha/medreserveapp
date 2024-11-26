@RestController
@RequestMapping("/api/reserveClinic")
public class ReserveClinicController {

	@Autowired
	private ReserveClinicService reserveClinicService;

	// Endpoint para realizar una reserva
	@PostMapping("/reserve")
	public ResponseEntity<String> reserveClinic(@RequestBody ReserveClinicRequest request) {
		boolean success = reserveClinicService.createReservation(request);
		if (success) {
			return ResponseEntity.ok("Reserva realizada con éxito.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al realizar la reserva.");
		}
	}

	// Endpoint para obtener todas las reservas
	@GetMapping("/reservations")
	public ResponseEntity<List<ReserveClinic>> getAllReservations() {
		List<ReserveClinic> reservations = reserveClinicService.getAllReservations();
		return ResponseEntity.ok(reservations);
	}
}
