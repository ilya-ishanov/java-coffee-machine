package kz.solvatech.testtask.service;

import kz.solvatech.testtask.dto.PublicHolidayDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class OperationalService {
    public static final String URL = "https://date.nager.at/api/v3/PublicHolidays/2025/KZ";
    private final RestTemplate restTemplate = new RestTemplate();
    private final Set<LocalDate> holidays = new HashSet<>();
    private static final Logger logger = LoggerFactory.getLogger(OperationalService.class);


    public boolean isWorkingHours() {
        return isCoffeeMachineOperational();
    }

    private boolean isCoffeeMachineOperational() {
        LocalDateTime now = LocalDateTime.now();
        LocalTime currentTime = now.toLocalTime();
        LocalDate currentDate = now.toLocalDate();

        return !currentTime.isBefore(LocalTime.of(8, 0))
                && !currentTime.isAfter(LocalTime.of(17, 0))
                && !isWeekend(currentDate)
                && !isHoliday(currentDate);
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6;
    }

    private boolean isHoliday(LocalDate date) {
        if (holidays.isEmpty()) {
            holidays.addAll(fetchHolidays());
        }
        return holidays.contains(date);
    }

    @Cacheable("holidays")
    public List<LocalDate> fetchHolidays() {
        log.info("Fetching holidays from API for year");
        logger.info("Fetching holidays from external API");
        PublicHolidayDto[] publicHolidays = restTemplate.getForObject(URL, PublicHolidayDto[].class);
        if (publicHolidays != null) {
            return Arrays.stream(publicHolidays).map(PublicHolidayDto::getDate).toList();
        }
        System.out.println("Праздники: " + holidays);
        return Collections.emptyList();
    }
}
