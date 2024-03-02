package ua.deti.tqs.lab3_2.services;

import org.springframework.stereotype.Service;
import ua.deti.ies.models.Car;
import ua.deti.ies.dao.CarRepository;

import java.util.List;

@Service
public class CarManagerService {
    @Autowired
    private CarRepository carRepository;

    public Car createCar(Car car){

    }

    public Car getCarById(Long id){

    }

    public List<Car> getAllCars(){

    }
}