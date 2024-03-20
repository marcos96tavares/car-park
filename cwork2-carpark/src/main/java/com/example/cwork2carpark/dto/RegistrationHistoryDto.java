package com.example.cwork2carpark.dto;

import com.example.cwork2carpark.entity.RegistrationHistory;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationHistoryDto {


    private Long registrationHistoryId;
    private String carNumberPlateHistory;
    private String driverFirstName;
    private String driverLastName;
    private String duration;
    private LocalDate starDate;
    private LocalTime startTime;




}
