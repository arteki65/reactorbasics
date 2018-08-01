package pl.aptewicz.reactor.util;

import org.springframework.stereotype.Component;

@Component
public class LogMessageHelper {

    public String formatThreadBlockingTime(Class<?> clazz, long startTime, long endTime) {
        return "⏱ Thread in " + clazz.getSimpleName() + " was blocked through " + (endTime - startTime) + " ns.";
    }
}
