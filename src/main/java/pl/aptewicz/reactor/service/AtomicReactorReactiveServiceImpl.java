package pl.aptewicz.reactor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.aptewicz.reactor.dto.AtomicReactorDto;
import pl.aptewicz.reactor.mapper.AtomicReactorMapper;
import pl.aptewicz.reactor.repository.AtomicReactorReactiveRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AtomicReactorReactiveServiceImpl implements AtomicReactorReactiveService {

    private static final Logger LOG = LoggerFactory.getLogger(AtomicReactorReactiveServiceImpl.class);

    private final AtomicReactorMapper mapper;

    private final AtomicReactorReactiveRepository repository;

    public AtomicReactorReactiveServiceImpl(AtomicReactorMapper mapper, AtomicReactorReactiveRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Mono<AtomicReactorDto> create(Mono<AtomicReactorDto> atomicReactorDto) {
        return atomicReactorDto.map(this.mapper::toEntity).flatMap(this.repository::save).map(this.mapper::toDto);

        // diff between doOn... and map/flatMap
        /*return atomicReactorDto.map(this.mapper::toEntity).doOnNext(entity -> {
            LOG.info("doOnNext");
            this.repository.save(entity);
        }).map(this.mapper::toDto).log();*/

        //blocking approach
        /*return this.repository.save(this.mapper.toEntity(atomicReactorDto.block())).map(this.mapper::toDto);*/
    }

    @Override
    public Flux<AtomicReactorDto> findAll() {
        return this.repository.findWithTailableCursorBy().map(this.mapper::toDto).log();
    }
}
