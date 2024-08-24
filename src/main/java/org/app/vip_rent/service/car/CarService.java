package org.app.vip_rent.service.car;

import lombok.RequiredArgsConstructor;
import org.app.vip_rent.modal.entity.car.Car;
import org.app.vip_rent.modal.entity.fuel_type.FuelType;
import org.app.vip_rent.repository.car.CarRepository;
import org.app.vip_rent.repository.fuel_type.FuelTypeRepository;
import org.app.vip_rent.request.CarRequest;
import org.app.vip_rent.result.DataResult;
import org.app.vip_rent.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService implements ICarService {
    private final CarRepository carRepository;
    private final FuelTypeRepository fuelTypeRepository;
    @Override
    public DataResult<List<Car>> getAllCars() {
        List<Car> carList = carRepository.findAll();
        if(carList.isEmpty())
            return new DataResult<>(null, Result.showMessage(Result.SUCCESS,"Cars not found"));
        return new DataResult<>(carList, Result.showMessage(Result.SUCCESS,"Cars listed successfully"));
    }

    @Override
    public DataResult<Car> getCarById(Long id) {
        if(id == null)
            return new DataResult<>(null, Result.showMessage(Result.SERVER_ERROR,"Car id cannot be empty"));
        Optional<Car> car = carRepository.findById(id);
        if(car.isPresent())
            return new DataResult<>(car.get(), Result.showMessage(Result.SUCCESS,"Car found successfully"));
        return new DataResult<>(null, Result.showMessage(Result.SUCCESS,"Car not found"));
    }

    private Result checkConstraints(CarRequest carRequest)
    {
        if(carRequest.getBrand() == null || carRequest.getBrand().isEmpty())
            return Result.showMessage(Result.SERVER_ERROR,"Brand and model cannot be empty");
        if(carRequest.getYear() == null || carRequest.getYear().isEmpty())
            return Result.showMessage(Result.SERVER_ERROR,"Year cannot be empty");
        if(carRequest.getDescription() == null || carRequest.getDescription().isEmpty() )
            return Result.showMessage(Result.SERVER_ERROR,"Description cannot be empty");
        if(carRequest.getPlate() == null || carRequest.getPlate().isEmpty())
            return Result.showMessage(Result.SERVER_ERROR,"Plate cannot be empty");
        if(carRequest.getModel() == null || carRequest.getModel().isEmpty())
            return Result.showMessage(Result.SERVER_ERROR,"Modal cannot be empty");
        if(carRequest.getFuelType() == null )
            return Result.showMessage(Result.SERVER_ERROR,"Fuel type cannot be empty");
        return Result.showMessage(Result.SUCCESS,"Constraints are valid");
    }

    @Override
    public Result saveCar(CarRequest carRequest) {
        Result result = checkConstraints(carRequest);
        if(result.getResultCode() == 1)
            return result;

        Optional<FuelType> fuelType = fuelTypeRepository.findFuelTypeByFuelType(carRequest.getFuelType());
        if(fuelType.isEmpty())
            return Result.showMessage(Result.SERVER_ERROR,"Fuel type not found");
        Car createdCar = Car.builder()
                .brand(carRequest.getBrand())
                .model(carRequest.getModel())
                .year(carRequest.getYear())
                .description(carRequest.getDescription())
                .plate(carRequest.getPlate())
                .fuelType(fuelType.get())
                .build();
        try
        {
            carRepository.save(createdCar);
            return Result.showMessage(Result.SUCCESS,"Car saved successfully");
        }
        catch (Exception e)
        {
            return Result.showMessage(Result.SERVER_ERROR,"An error occurred while saving the car");
        }
    }

    @Override
    public Result updateCar(Long carId, CarRequest carRequest) {
        Optional<Car> car = carRepository.findById(carId);
        if(car.isEmpty())
            return Result.showMessage(Result.SERVER_ERROR,"Car not found");

        Result result = checkConstraints(carRequest);
        if(result.getResultCode() == 1)
            return result;

        Optional<FuelType> fuelType = fuelTypeRepository.findFuelTypeByFuelType(carRequest.getFuelType());

        Car updatedCar = car.get();
        updatedCar.setBrand(carRequest.getBrand());
        updatedCar.setModel(carRequest.getModel());
        updatedCar.setYear(carRequest.getYear());
        updatedCar.setDescription(carRequest.getDescription());
        updatedCar.setPlate(carRequest.getPlate());
        updatedCar.setFuelType(fuelType.get());
        try
        {
            carRepository.save(updatedCar);
            return Result.showMessage(Result.SUCCESS,"Car updated successfully");
        }
        catch (Exception e)
        {
            return Result.showMessage(Result.SERVER_ERROR,"An error occurred while updating the car");
        }
    }

    @Override
    public Result deleteCar(Long carId) {
        if(carId == null)
            return Result.showMessage(Result.SERVER_ERROR,"Car id cannot be empty");
        Optional<Car> car = carRepository.findById(carId);
        if(car.isPresent()){
            try {
                carRepository.delete(car.get());
                return Result.showMessage(Result.SUCCESS,"Car deleted successfully");
            }
            catch (Exception e){
                return Result.showMessage(Result.SERVER_ERROR,"An error occurred while deleting the car");
            }
        }
        return Result.showMessage(Result.SUCCESS,"Car not found");
    }
}
