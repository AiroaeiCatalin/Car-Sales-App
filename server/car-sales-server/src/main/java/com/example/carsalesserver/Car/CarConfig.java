//package com.example.carsalesserver.Car;
//
//import com.example.carsalesserver.Car.enums.EngineType;
//import com.example.carsalesserver.Car.enums.TransmissionType;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.List;
//
//import static java.time.Month.APRIL;
//import static java.time.Month.JANUARY;
//
//@Configuration
//public class CarConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunner(
//            CarRepository repository) {
//        return args -> {
//            Car skoda = new Car(
//                    "Skoda",
//                    "Octavia vRS",
//                    38100,
//                    LocalDate.of(2020, JANUARY, 20),
//                    23822,
//                    EngineType.PETROL,
//                    TransmissionType.AUTOMATIC,
//                    280
//            );
//            Car bmw = new Car(
//                    "BMW",
//                    "M3",
//                    51200,
//                    LocalDate.of(2019, APRIL, 15),
//                    17021,                    EngineType.PETROL,
//                    TransmissionType.AUTOMATIC,
//                    540
//            );
//
//            repository.saveAll(List.of(skoda, bmw));
//        };
//    }
//
//}
