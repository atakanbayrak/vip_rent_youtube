package org.app.vip_rent.controller.car;

import lombok.RequiredArgsConstructor;
import org.app.vip_rent.modal.entity.car.Car;
import org.app.vip_rent.request.CarRequest;
import org.app.vip_rent.result.DataResult;
import org.app.vip_rent.result.Result;
import org.app.vip_rent.service.car.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/car/v1")
@RestController
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("getAllCars")
    public DataResult<List<Car>> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("getCarById")
    public DataResult<Car> getCarById(@RequestParam Long id) {
        return carService.getCarById(id);
    }

    @PostMapping("saveCar")
    public Result saveCar(@RequestBody CarRequest car) {
        return carService.saveCar(car);
    }

    @PostMapping("updateCar")
    public Result updateCar(@RequestParam Long carId, @RequestBody CarRequest car) {
        return carService.updateCar(carId, car);
    }

    @PostMapping("deleteCar")
    public Result deleteCar(@RequestParam Long carId) {
        return carService.deleteCar(carId);
    }

}
