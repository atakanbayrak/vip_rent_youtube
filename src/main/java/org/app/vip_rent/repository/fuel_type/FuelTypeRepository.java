package org.app.vip_rent.repository.fuel_type;

import org.app.vip_rent.enums.FuelTypes;
import org.app.vip_rent.modal.entity.fuel_type.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {
    Optional<FuelType> findFuelTypeByFuelType(FuelTypes fuelType);
}
