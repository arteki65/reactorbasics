package pl.aptewicz.reactor.mapper;

import org.mapstruct.Mapper;
import pl.aptewicz.reactor.dto.AtomicReactorDto;
import pl.aptewicz.reactor.model.AtomicReactor;

@Mapper(componentModel = "spring")
public interface AtomicReactorMapper {

    AtomicReactorDto toDto(AtomicReactor atomicReactor);

    AtomicReactor toEntity(AtomicReactorDto dto);
}
