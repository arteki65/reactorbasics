package pl.aptewicz.reactor.service;

import pl.aptewicz.reactor.dto.AtomicReactorDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AtomicReactorReactiveService {

    Mono<AtomicReactorDto> create(Mono<AtomicReactorDto> atomicReactorDto);

    Flux<AtomicReactorDto> findAll();
}
