package com.example.cwork2carpark.service;

import com.example.cwork2carpark.dto.RegistrationDto;
import com.example.cwork2carpark.entity.Registration;

import java.util.List;

public interface RegistrationServices {

    List<RegistrationDto> getAllRegistration();

    Registration saveRegistration(RegistrationDto registrationDto);

    String upDateRegistration(RegistrationDto registrationDto, Long id);

    String delecteRegistrationById(Long id);

    List<RegistrationDto> sortByStatus(String status);

    List<RegistrationDto> searchRegistration(String query);

    Registration findRegistrationById(Long id);
}
