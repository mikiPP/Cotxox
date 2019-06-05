package org.lasencinas.cotxox.Model.Dto.Fare;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FareFilterDto {

    private Double mileage;
    private Double cost;
    private Long userId;
    private Long driverId;
}
