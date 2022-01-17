package com.example.carsalesserver.Car;

import com.example.carsalesserver.Ad.Ad;
import com.example.carsalesserver.Ad.AdRepository;
import com.example.carsalesserver.Car.utils.EngineType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final AdRepository adRepository;

    @Autowired
    public CarService(CarRepository carRepository, AdRepository adRepository) {
        this.carRepository = carRepository;
        this.adRepository = adRepository;
    }

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public void addNewCar(Car car) {
//        Optional<Car> carOptional =  carRepository.findCarByManufacturer(car.getManufacturer());
//        if(carOptional.isPresent()) {
//            throw new IllegalStateException("manufacturer taken");
//        }
        Ad ad = new Ad();
        ad.setCar(car);
        ad.setDescription("macmac");
        adRepository.save(ad);
//        carRepository.save(car);
    }


    public void deleteCar(Long carId) {
        boolean carExists = carRepository.existsById(carId);
        if(!carExists) {
            throw new IllegalStateException( "car with id " + carId +" does not exist");
        }
        carRepository.deleteById(carId);
    }



    //cu transactional cand dau setkm de ex mi se da update instant in baza d date
    @Transactional
    public void updateCar(Long carId, Integer km, Integer price, EngineType engineType) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalStateException("car with id " + carId +" does not exist"));

        if(km != null &&
                !Objects.equals(car.getKm(), km)) {
            car.setKm(km);
        }

        if(price != null &&
                !Objects.equals(car.getPrice(), price)) {
            car.setPrice(price);
        }

        if(engineType != null &&
                !Objects.equals(car.getEngineType(), engineType)) {
            car.setEngineType(engineType);
        }

    }

    public List<String> getManufacturers() {
        return carRepository.findDistinctManufacturers();
    }

    public List<String> getModels(String manufacturer) {
        return carRepository.findDistinctModelsByManufacturers(manufacturer);
    }
}
