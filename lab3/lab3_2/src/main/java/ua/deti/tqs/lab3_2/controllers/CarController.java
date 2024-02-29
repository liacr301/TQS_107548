package ua.deti.tqs.lab3_2.controllers;

import ua.deti.tqs.lab3_2.services.CarManagerService;
import ua.deti.tqs.lab3_2.models.Car;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarManagerService carManagerService;


    @PostMapping("/car")
    public ResponseEntity<Car> createCar(@RequestBody Car car){

    }

    @GetMapping("/car")
    public RespondeEntity<List<Car>> getAllCars(){

    }

    @GetMapping("/car/{id}")
    public ResponseEntity<Car> getCarById(Long id){

    }
}