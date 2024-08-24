package org.app.vip_rent.service.car;

import org.app.vip_rent.modal.entity.car.Car;
import org.app.vip_rent.modal.entity.user.User;
import org.app.vip_rent.request.CarRequest;
import org.app.vip_rent.request.UserRequest;
import org.app.vip_rent.result.DataResult;
import org.app.vip_rent.result.Result;

import java.util.List;

public interface ICarService {
    DataResult<List<Car>> getAllCars();
    DataResult<Car> getCarById(Long id);

    Result saveCar(CarRequest carRequest);
    Result updateCar(Long carId, CarRequest carRequest);
    Result deleteCar(Long carId);
}
