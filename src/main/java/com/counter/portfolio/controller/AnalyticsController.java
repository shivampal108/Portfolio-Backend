package com.counter.portfolio.controller;


import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {

    private final AtomicInteger totalVisits = new AtomicInteger(109);
    private final Set<String> visitors = ConcurrentHashMap.newKeySet();

    @PostMapping("/visit")
    public Map<String, Integer> trackVisit(
            @RequestBody Map<String, String> request) {

        String visitorId = request.get("visitorId");

        if (visitorId != null && !visitorId.isBlank()) {
            if (visitors.add(visitorId)) {
                totalVisits.incrementAndGet();
            }
        }

        return Map.of("totalVisits", totalVisits.get());
    }

    @GetMapping("/count")
    public Map<String, Integer> getCount() {
        return Map.of("totalVisits", totalVisits.get());
    }
}
