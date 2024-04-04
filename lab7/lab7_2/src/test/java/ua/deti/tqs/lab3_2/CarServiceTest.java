package ua.deti.tqs.lab3_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.deti.tqs.lab3_2.models.Car;
import ua.deti.tqs.lab3_2.dao.CarRepository;
import ua.deti.tqs.lab3_2.services.CarManagerService;

import java.util.Arrays;
import java.util.List;

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

        when(carRepository.findByCarId(111L)).thenReturn(car_1);
        when(carRepository.findByCarId(-40L)).thenReturn(null);

        List<Car> allCars = Arrays.asList(car_1, car_2, car_3);
        when(carRepository.findAll()).thenReturn(allCars);
    }

    @Test
    void whenSearchValidId_thenCarShouldBeFound() {
        Long car_1_id = 111L;
        Car found = carService.getCarById(car_1_id);

        assertThat(found.getModel()).isEqualTo("M4");
    }

    @Test
    void whenSearchInvalidId_thenCarShouldNotBeFound() {
        Car fromDb = carService.getCarById(-40L);
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
        assertThat(allCars).hasSize(3).extracting(Car::getModel).contains(car_1.getModel(), car_2.getModel(), car_3.getModel());
    }

    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findByCarId(Mockito.anyLong());
    }

    private void verifyFindAllCarsIsCalledOnce() {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();
    }


}