package ua.deti.tqs.lab3_2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tqs_car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long carId;

    @Column(name = "maker")
    private String maker;

    @Column(name = "model")
    private String model;

    public Car () {
    }

    public Car (String maker, String model){
        this.maker = maker;
        this.model = model;
    }

    public Long getCarId() {
        return carId;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Car car = (Car) object;
        return java.util.Objects.equals(carId, car.carId) && java.util.Objects.equals(maker, car.maker) && java.util.Objects.equals(model, car.model);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), carId, maker, model);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Car{" +
                "carId=" + carId +
                ", maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}