package ua.deti.tqs.backend.services;

import ua.deti.tqs.backend.models.Reservation;
import ua.deti.tqs.backend.dao.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReservationService {
   @Autowired
   private ReservationRepository reservationRepository;
   
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation findReservationById(String token) {
         return reservationRepository.findByToken(token);
    }

    public String generateToken() {
        String uuid = java.util.UUID.randomUUID().toString();
        String token = uuid.replaceAll("-", "");
        return token;
    }
}
