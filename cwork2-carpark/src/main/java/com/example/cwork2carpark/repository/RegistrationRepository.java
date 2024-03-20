package com.example.cwork2carpark.repository;

import com.example.cwork2carpark.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {


    @Query("SELECT c FROM Registration c WHERE c.carNumberPlate LIKE CONCAT('%', :query, '%') OR c.driverFirstName LIKE CONCAT('%', :query, '%')")
    List<Registration> searchRegistration(@Param("query") String query);

}
