package ua.deti.tqs.lab3_2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository extends JpaRepository<Car, Long> {
    public Car findById(Long id);
    public List<Car> findAll();
}