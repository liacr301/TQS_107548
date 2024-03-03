package ua.deti.tqs.lab3_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.deti.tqs.models.Car;
import ua.deti.tqs.dao.CarRepository;
import ua.deti.tqs.services.CarManagerService;

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
        car_1.setId(111L);

        Car car_2 = new Car("Opel", "Astra");
        Car car_3 = new Car("Nissan", "Qashqai");

        List<Car> allCars = Arrays.asList(car_1, car_2, car_3);

        Mockito.when(carRepository.findById(car_1.getId())).thenReturn(car_1);
        Mockito.when(carRepository.findById(car_2.getId())).thenReturn(car_2);
        Mockito.when(carRepository.findById(675L)).thenReturn(null);
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
        Mockito.when(carRepository.findById(-99L)).thenReturn(Optional.empty());
    }

    @Test
    void whenSearchValidId_thenCarShouldBeFound() {
        String name = "alex";
        Employee found = employeeService.getEmployeeByName(name);

        assertThat(found.getName()).isEqualTo(name);
    }


}