package org.app.vip_rent.modal.entity.fuel_type;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.vip_rent.enums.FuelTypes;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fuel_types")
public class FuelType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fuelTypeId;

    @Column(name = "fuel_type_name")
    @Enumerated(EnumType.STRING)
    private FuelTypes fuelType;

    @Column(name = "description")
    private String description;
}
