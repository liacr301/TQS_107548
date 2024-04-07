package ua.deti.tqs.backend.dao;

import ua.deti.tqs.backend.models.Reservation;
import ua.deti.tqs.backend.dao.ReservationRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ReservationRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void setUp() {
        entityManager.clear();
    }

    @Test
    @DisplayName("Test find reservation by token")
    public void testFindReservationByToken() {
        Reservation reservation = new Reservation(0, "Token", "fromCity", "toCity", "date", "time", "firstName", "lastName", "email");
        entityManager.persistAndFlush(reservation);
        Reservation found = reservationRepository.findByToken("Token");
        assertThat(found.getToken()).isEqualTo(reservation.getToken());
    }

    @Test
    @DisplayName("Test find reservation by invalid token")
    public void testFindReservationByInvalidToken() {
        Reservation found = reservationRepository.findByToken("Invalid");
        assertThat(found).isNull();
    }
}
