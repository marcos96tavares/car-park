package com.example.cwork2carpark.dto;

import com.example.cwork2carpark.entity.Registration;
import com.example.cwork2carpark.entity.RegistrationHistory;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class RegistrationDto {


    private Long registrationId;
    @NotEmpty(message = "The Car Number Plate Should not be empty")
    private String carNumberPlate;
    @NotEmpty(message = "The Driver name should not be empty")
    private String driverFirstName;
    private String driverLastName;
    private String additionalComments;


    private LocalDate starDate;

    private LocalTime startTime;

    private LocalDate finishDate;

    private LocalTime finishTime;


    private final String status;
    private final String duration;







}
