package org.app.vip_rent.request;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.vip_rent.enums.FuelTypes;
import org.app.vip_rent.modal.entity.fuel_type.FuelType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarRequest {

    private String brand;

    private String model;

    private String plate;

    private String year;

    private String description;

    private FuelTypes fuelType;
}
