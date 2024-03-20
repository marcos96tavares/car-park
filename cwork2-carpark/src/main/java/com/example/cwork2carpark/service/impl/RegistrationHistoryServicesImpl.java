package com.example.cwork2carpark.service.impl;

import com.example.cwork2carpark.dto.RegistrationHistoryDto;
import com.example.cwork2carpark.entity.Registration;
import com.example.cwork2carpark.entity.RegistrationHistory;
import com.example.cwork2carpark.repository.RegistrationHistoryRepository;
import com.example.cwork2carpark.repository.RegistrationRepository;
import com.example.cwork2carpark.service.RegistrationHistoryServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RegistrationHistoryServicesImpl implements RegistrationHistoryServices {

    private RegistrationHistoryRepository registrationHistoryRepository;
    private RegistrationRepository registrationRepository;

    public RegistrationHistoryServicesImpl(RegistrationHistoryRepository registrationHistoryRepository, RegistrationRepository registrationRepository) {
        this.registrationHistoryRepository = registrationHistoryRepository;
        this.registrationRepository = registrationRepository;
    }

    @Override
    public void saveRegistrationHistory( Long id) {

        Registration registration = registrationRepository.findById(id).get();

        registrationHistoryRepository.save(dtoToRegistrationHistory(registration, registration));

    }

    @Override
    public List<RegistrationHistoryDto> listOfHistoryById(Long id) {

        List<RegistrationHistory> registrationHistoryList =  registrationHistoryRepository.findByRegistrationRegistrationId(id);

        return registrationHistoryList.stream()
                .map(this::registrationToDto)
                .collect(Collectors.toList());

    }


    private RegistrationHistory dtoToRegistrationHistory(Registration registrationDto, Registration store){



        return RegistrationHistory.builder()
                .driverFirstName(registrationDto.getDriverFirstName().toUpperCase())
                .driverLastName(registrationDto.getDriverLastName().toUpperCase())
                .starDate(registrationDto.getStarDate())
                .startTime(registrationDto.getStartTime())
                .carNumberPlateHistory(registrationDto.getCarNumberPlate().toUpperCase())
                .registration(store)
                .duration(registrationDto.getDuration())
                .build();
    }


    private RegistrationHistoryDto registrationToDto(RegistrationHistory registrationHistory){

        return RegistrationHistoryDto.builder()
                .registrationHistoryId(registrationHistory.getRegistrationHistoryId())
                .carNumberPlateHistory(registrationHistory.getCarNumberPlateHistory())
                .driverFirstName(registrationHistory.getDriverFirstName())
                .driverLastName(registrationHistory.getDriverLastName())
                .starDate(registrationHistory.getStarDate())
                .startTime(registrationHistory.getStartTime())
                .duration(registrationHistory.getDuration())
                .build();
    }
}
