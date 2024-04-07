package ua.deti.tqs.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.deti.tqs.backend.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Reservation findByToken(String token);
}
