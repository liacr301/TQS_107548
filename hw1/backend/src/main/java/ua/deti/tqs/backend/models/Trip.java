package ua.deti.tqs.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "TBL_TRIPS")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int bus;
    private String fromCity;
    private String toCity;
    private String dateTrip;
    private String timeTrip;
    private double price;
    private int availableSeats;

    public Trip() {
    }

    public Trip(int bus, String fromCity, String toCity, String dateTrip, String timeTrip, double price, int availableSeats) {
        this.bus = bus;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.dateTrip = dateTrip;
        this.timeTrip = timeTrip;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    public int getId() {
        return id;
    }

    public int getBus() {
        return bus;
    }

    public String getFromCity() {
        return fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public String getDateTrip() {
        return dateTrip;
    }

    public String getTimeTrip() {
        return timeTrip;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBus(int bus) {
        this.bus = bus;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public void setDateTrip(String dateTrip) {
        this.dateTrip = dateTrip;
    }

    public void setTimeTrip(String timeTrip) {
        this.timeTrip = timeTrip;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

}
