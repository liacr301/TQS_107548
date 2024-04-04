package ua.deti.tqs.lab3_2.controllers;

import ua.deti.tqs.lab3_2.services.CarManagerService;
import ua.deti.tqs.lab3_2.models.Car;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarManagerService carManagerService;

    @PostMapping("/car")
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        return ResponseEntity.status(HttpStatus.CREATED).body(carManagerService.createCar(car));
    }

    @GetMapping("/car")
    public ResponseEntity<List<Car>> getAllCars(){
        return ResponseEntity.status(HttpStatus.OK).body(carManagerService.getAllCars());
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long id){
        Car car = carManagerService.getCarById(id);
        HttpStatus code;

        if (car == null) {
            code = HttpStatus.NOT_FOUND;
        } else {
            code = HttpStatus.OK;
        }

        return new ResponseEntity<Car>(car, code);
    }
}