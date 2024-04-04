package ua.deti.tqs.lab3_2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import ua.deti.tqs.lab3_2.models.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    public Car findByCarId(Long id);
    public List<Car> findAll();
}