package pl.aptewicz.reactor.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AtomicReactorDto {

    private String id;

    private String name;

    private BigDecimal probabilityOfExplosion;
}
