package kz.solvatech.testtask.service;

import kz.solvatech.testtask.dto.PublicHolidayDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OperationalService {
    @Value("${operational.holidaysUrl}")
    private String holidaysUrl;

    @Value("${operational.workStart}")
    private String workStartRaw;

    @Value("${operational.workEnd}")
    private String workEndRaw;

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

        LocalTime workStart = LocalTime.parse(workStartRaw);
        LocalTime workEnd = LocalTime.parse(workEndRaw);

        return !currentTime.isBefore(workStart)
                && !currentTime.isAfter(workEnd)
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
        PublicHolidayDto[] publicHolidays = restTemplate.getForObject(holidaysUrl, PublicHolidayDto[].class);
        if (publicHolidays != null) {
            return Arrays.stream(publicHolidays).map(PublicHolidayDto::getDate).toList();
        }
        System.out.println("Праздники: " + holidays);
        return Collections.emptyList();
    }
}
