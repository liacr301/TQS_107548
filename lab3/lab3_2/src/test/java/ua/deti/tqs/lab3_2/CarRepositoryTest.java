package ua.deti.tqs.lab3_2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import ua.deti.tqs.lab3_2.models.Car;
import ua.deti.tqs.lab3_2.dao.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    void whenFindByIdCar1_thenReturnCar1() {
        // arrange a new employee and insert into db
        Car car_1 = new Car("BMW", "M4");
        entityManager.persistAndFlush(car_1); //ensure data is persisted at this point

        // test the query method of interest
        Car found = carRepository.findByCarId(car_1.getCarId());
        assertThat(found).isEqualTo(car_1);
    }

    @Test
    void whenInvalidCarId_thenReturnNull() {
        Car fromDb = carRepository.findByCarId(null);
        assertThat(fromDb).isNull();
    }

    @Test
    void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car car_1 = new Car("BMW", "M4");
        Car car_2 = new Car("Opel", "Astra");
        Car car_3 = new Car("Nissan", "Qashqai");

        entityManager.persist(car_1);
        entityManager.persist(car_2);
        entityManager.persist(car_3);
        entityManager.flush();

        List<Car> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getModel).containsOnly(car_1.getModel(), car_2.getModel(), car_3.getModel());
    }

}