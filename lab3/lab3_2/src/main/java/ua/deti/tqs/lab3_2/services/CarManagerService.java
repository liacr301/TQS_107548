package ua.deti.tqs.lab3_2.services;

import org.springframework.stereotype.Service;
import ua.deti.tqs.lab3_2.models.Car;
import ua.deti.tqs.lab3_2.dao.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CarManagerService {

    @Autowired
    private CarRepository carRepository;

    public Car createCar(Car car){
        return carRepository.save(car);
    }

    public Car getCarById(Long id){
        return carRepository.findByCarId(id);
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }
}