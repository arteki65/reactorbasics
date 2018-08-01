package pl.aptewicz.reactor.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.aptewicz.reactor.dto.AtomicReactorDto;
import pl.aptewicz.reactor.service.AtomicReactorReactiveService;
import pl.aptewicz.reactor.util.LogMessageHelper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/atomic-reactor")
public class AtomicReactorReactiveController {

    private static final Logger LOG = LoggerFactory.getLogger(AtomicReactorReactiveController.class);

    private final AtomicReactorReactiveService atomicReactorReactiveService;

    private final LogMessageHelper logMessageHelper;

    public AtomicReactorReactiveController(AtomicReactorReactiveService atomicReactorReactiveService,
            LogMessageHelper logMessageHelper) {
        this.atomicReactorReactiveService = atomicReactorReactiveService;
        this.logMessageHelper = logMessageHelper;
    }

    @PutMapping
    public Mono<AtomicReactorDto> createAtomicReactor(@RequestBody Mono<AtomicReactorDto> atomicReactorDto) {
        long start = System.nanoTime();
        Mono<AtomicReactorDto> response = this.atomicReactorReactiveService.create(atomicReactorDto);
        long end = System.nanoTime();

        LOG.info(logMessageHelper.formatThreadBlockingTime(this.getClass(), start, end));

        return response;
    }

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<AtomicReactorDto> findAll() {
        return this.atomicReactorReactiveService.findAll();
    }
}
