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
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Registration implements Comparable<Registration> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationId;
    private String carNumberPlate;
    private String driverFirstName;
    private String driverLastName;
    private String additionalComments;
    private LocalDate starDate;

    private LocalTime startTime;

    private LocalDate finishDate;

    private LocalTime finishTime;



    // One-to-many relationship with RegistrationHistory
    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistrationHistory> historyList;


    // This method is the duration of the that the car will stay.
    public String getDuration() {
        if (startTime != null && finishTime != null) {
//
            Duration duration = Duration.between(startTime, finishTime);

            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;


            return String.format("%02d:%02d", hours, minutes);
        } else {
            // handle the case where startTime or finishTime is null
            return null;
        }
    }




    //
    public String getStatus() {
        LocalDate now = LocalDate.now();

        if (finishDate != null) {
            if (finishDate.isBefore(now)) {
                return "EXPIRED";
            } else if (finishDate.isEqual(now)) {
                if (finishTime != null && finishTime.isBefore(LocalTime.now())) {
                    return "EXPIRED";
                } else {
                    return "ACTIVE";
                }
            } else {
                return "ACTIVE";
            }
        } else {
            return "NOT EXPIRED";
        }
    }



    @Override
    public int compareTo(Registration o) {
        return this.registrationId.compareTo(o.registrationId);
    }



}
