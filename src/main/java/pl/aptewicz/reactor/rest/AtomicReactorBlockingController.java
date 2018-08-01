package pl.aptewicz.reactor.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.aptewicz.reactor.dto.AtomicReactorDto;
import pl.aptewicz.reactor.service.AtomicReactorBlockingService;
import pl.aptewicz.reactor.util.LogMessageHelper;

@RestController
@RequestMapping("/blocking/atomic-reactor")
public class AtomicReactorBlockingController {

    private static final Logger LOG = LoggerFactory.getLogger(AtomicReactorBlockingController.class);

    private final AtomicReactorBlockingService service;

    private final LogMessageHelper logMessageHelper;

    public AtomicReactorBlockingController(AtomicReactorBlockingService service, LogMessageHelper logMessageHelper) {
        this.service = service;
        this.logMessageHelper = logMessageHelper;
    }

    @PutMapping
    public AtomicReactorDto createAtomicReactor(@RequestBody AtomicReactorDto dto) {
        long start = System.nanoTime();
        AtomicReactorDto response = this.service.create(dto);
        long end = System.nanoTime();

        LOG.info(logMessageHelper.formatThreadBlockingTime(this.getClass(), start, end));

        return response;
    }
}
