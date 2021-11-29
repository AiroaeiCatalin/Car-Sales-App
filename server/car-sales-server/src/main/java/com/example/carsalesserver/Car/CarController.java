package com.example.carsalesserver.Car;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/v1/car")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

//    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<Car> getCars() {
        return carService.getCars();
    }

//    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "manufacturers")
    public List<String> getManufacturers() { return carService.getManufacturers();}

    @GetMapping(path = "{manufacturer}/models")
    public List<String> getModels(@PathVariable("manufacturer") String manufacturer) { return carService.getModels(manufacturer);}


    @PostMapping
    public void registerNewCar(@RequestBody Car car){
        carService.addNewCar(car);
    }

    @DeleteMapping(path = "{carId}")
    public void deleteCar(@PathVariable("carId") Long carId ){
        carService.deleteCar(carId);
    }

//    @PutMapping(path = "{carId}")
//    public void updateCar(
//            @PathVariable("carId") Long carId,
//            @RequestParam(required = false) Integer km,
//            @RequestParam(required = false) Integer price,
//            @RequestParam(required = false) EngineType engineType) {
//        carService.updateCar(carId, km, price, engineType);
//    }
    @PutMapping(path = "{carId}")
    public void updateCar(
            @RequestBody Car car,
            @PathVariable("carId") Long carId
            ) {
        carService.updateCar(carId, car.getKm(), car.getPrice(), car.getEngineType());
    }


}



