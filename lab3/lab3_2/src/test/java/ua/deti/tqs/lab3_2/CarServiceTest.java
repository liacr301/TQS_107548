package ua.deti.tqs.lab3_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.deti.tqs.lab3_2.models.Car;
import ua.deti.tqs.lab3_2.dao.CarRepository;
import ua.deti.tqs.lab3_2.services.CarManagerService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carService;

    @Test
    @BeforeEach
    public void setUp() {
        Car car_1 = new Car("BMW", "M4");
        car_1.setCarId(111L);

        Car car_2 = new Car("Opel", "Astra");
        Car car_3 = new Car("Nissan", "Qashqai");

        List<Car> allCars = Arrays.asList(car_1, car_2, car_3);

        Mockito.when(carRepository.findById(car_1.getCarId())).thenReturn(car_1);
        Mockito.when(carRepository.findById(car_2.getCarId())).thenReturn(car_2);
        Mockito.when(carRepository.findById(675L)).thenReturn(null);
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
        Mockito.when(carRepository.findById(-99L)).thenReturn(Optional.empty());
    }

    @Test
    void whenSearchValidId_thenCarShouldBeFound() {
        Long car_1_id = 111L;
        Car found = carService.getCarById(car_1_id);

        assertThat(found.getCarId()).isEqualTo(car_1_id);
    }

    @Test
    void whenSearchInvalidId_thenCarShouldNotBeFound() {
        Car fromDb = carService.getCarById(675L);
        assertThat(fromDb).isNull();

        verifyFindByIdIsCalledOnce();
    }

    @Test
    void given3Cars_whengetAll_thenReturn3Records() {
        Car car_1 = new Car("BMW", "M4");
        Car car_2 = new Car("Opel", "Astra");
        Car car_3 = new Car("Nissan", "Qashqai");

        List<Car> allCars = carService.getAllCars();
        verifyFindAllCarsIsCalledOnce();
        assertThat(allCars).hasSize(3).extracting(Car::getCarId).contains(car_1.getCarId(), car_2.getCarId(), car_3.getCarId());
    }

    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
    }

    private void verifyFindAllCarsIsCalledOnce() {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();
    }

}