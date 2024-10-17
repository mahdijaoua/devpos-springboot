package tn.esprit.test.control;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.test.entity.Reservation;
import tn.esprit.test.service.IReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;


@Tag(name = "Gestion reservation")
@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationRestController {
    IReservationService ReservationService;
    // http://localhost:8089/tpfoyer/reservation/retrieve-all-reservations
    @Operation(description = "récupérer toutes les reservations de la base de données")
    @GetMapping("/retrieve-all-reservations")
    public List<Reservation> getreservations() {
        List<Reservation> listreservations = ReservationService.retrieveAllReservations();
        return listreservations;
    }
    // http://localhost:8089/tpfoyer/reservation/retrieve-reservation/8
    @GetMapping("/retrieve-reservation/{reservation-id}")
    public Reservation retrievereservation(@PathVariable("reservation-id") Long chId) {
        Reservation reservation = ReservationService.retrieveReservation(chId);
        return reservation;
    }
    // http://localhost:8089/tpfoyer/reservation/add-reservation
    @PostMapping("/add-reservation")
    public Reservation addreservation(@RequestBody Reservation c) {
        Reservation reservation = ReservationService.addReservation(c);
        return reservation;
    }
    // http://localhost:8089/tpfoyer/reservation/remove-reservation/{reservation-id}
    @DeleteMapping("/remove-reservation/{reservation-id}")
    public void removeReservation(@PathVariable("reservation-id") Long chId) {
        ReservationService.removeReservation(chId);
    }
    // http://localhost:8089/tpfoyer/reservation/modify-reservation
    @PutMapping("/modify-reservation")
    public Reservation modifyreservation(@RequestBody Reservation c) {
        Reservation reservation = ReservationService.modifyReservation(c);
        return reservation;
    }
}