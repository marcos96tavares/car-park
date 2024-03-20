package com.example.cwork2carpark.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class RegistrationHistory {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationHistoryId;
    private String carNumberPlateHistory;
    private String driverFirstName;
    private String driverLastName;
    private String duration;
    private LocalDate starDate;
    private LocalTime startTime;







    // Many-to-one relationship with Registration
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registration_id") // Specify the foreign key column
    private Registration registration;


}
