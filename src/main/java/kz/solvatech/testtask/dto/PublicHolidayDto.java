package kz.solvatech.testtask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PublicHolidayDto {

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("localName")
    private String localName;

    @JsonProperty("name")
    private String name;
}
