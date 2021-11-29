package com.example.carsalesserver.Car;

import com.example.carsalesserver.Car.utils.EngineType;
import com.example.carsalesserver.Car.utils.TransmissionType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;


@Entity
@Table
public class Car {
    @Id
    @SequenceGenerator(
            name = "car_sequence",
            sequenceName = "car_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "car_sequence"
    )


    private Long id;
    private String manufacturer;
    private String model;
    private Integer price;
    private LocalDate dateOfFabrication;
    private Integer km;


    @Transient
    private Integer age;

    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @Enumerated(EnumType.ORDINAL)
    private TransmissionType transmissionType;

    private Integer power;

    //pot sa mai am culoare, caroserie, motorizare daca e autoutilitara sau autovehicul si descrierea care dupa os a o mut in clasa anunt unde sa am si poze cu carusel,formurile alea tr sa le refac neaparat ca arata ca pula,

    public Car() {
    }

    public Car(String manufacturer, String model, Integer price, LocalDate dateOfFabrication, Integer km, EngineType engineType, TransmissionType transmissionType, Integer power) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
        this.dateOfFabrication = dateOfFabrication;
        this.km = km;
        this.engineType = engineType;
        this.transmissionType = transmissionType;
        this.power = power;
    }

    public Car(Long id, String manufacturer, String model, Integer price, LocalDate dateOfFabrication, Integer km, EngineType engineType, TransmissionType transmissionType, Integer power) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
        this.dateOfFabrication = dateOfFabrication;
        this.km = km;
        this.engineType = engineType;
        this.transmissionType = transmissionType;
        this.power = power;
    }

    public Long getId() {
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public LocalDate getDateOfFabrication() {
        return dateOfFabrication;
    }

    public Integer getKm() {
        return km;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setDateOfFabrication(LocalDate dateOfFabrication) {
        this.dateOfFabrication = dateOfFabrication;
    }

    public void setKm(Integer km) {
        this.km = km;
    }


    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getAge() {
        return Period.between(this.dateOfFabrication, LocalDate.now()).getYears();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", dateOfFabrication=" + dateOfFabrication +
                ", km=" + km +
                ", engineType=" + engineType +
                ", transmissionType=" + transmissionType +
                ", power=" + power +
                ", age=" + age +
                ", price=" + price +
                '}';
    }
}
