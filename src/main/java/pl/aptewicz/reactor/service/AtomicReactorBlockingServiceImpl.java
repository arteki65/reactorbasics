package pl.aptewicz.reactor.service;

import org.springframework.stereotype.Service;
import pl.aptewicz.reactor.dto.AtomicReactorDto;
import pl.aptewicz.reactor.mapper.AtomicReactorMapper;
import pl.aptewicz.reactor.repository.AtomicReactorBlockingRepository;

@Service
public class AtomicReactorBlockingServiceImpl implements AtomicReactorBlockingService {

    private final AtomicReactorBlockingRepository repository;

    private final AtomicReactorMapper mapper;

    public AtomicReactorBlockingServiceImpl(AtomicReactorBlockingRepository repository, AtomicReactorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AtomicReactorDto create(AtomicReactorDto atomicReactorDto) {
        return this.mapper.toDto(this.repository.save(this.mapper.toEntity(atomicReactorDto)));
    }
}
