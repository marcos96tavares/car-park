package com.example.cwork2carpark.service.impl;

import com.example.cwork2carpark.dto.RegistrationDto;
import com.example.cwork2carpark.entity.Registration;
import com.example.cwork2carpark.exceptionHandler.RegistrationException;
import com.example.cwork2carpark.repository.RegistrationHistoryRepository;
import com.example.cwork2carpark.repository.RegistrationRepository;
import com.example.cwork2carpark.service.RegistrationServices;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RegistrationServicesImpl implements RegistrationServices {

    private RegistrationRepository registrationRepository;
    private RegistrationHistoryRepository registrationHistoryRepository;

    public RegistrationServicesImpl(RegistrationRepository registrationRepository, RegistrationHistoryRepository registrationHistoryRepository) {
        this.registrationRepository = registrationRepository;
        this.registrationHistoryRepository = registrationHistoryRepository;
    }

    @Override
    public List<RegistrationDto> getAllRegistration() {

        List<Registration> registrations = registrationRepository.findAll();

        Collections.reverse(registrations);

        return registrations.stream()
                .map(this::registrationToDto)
                .collect(Collectors.toList());
    }

    @Override

    public Registration saveRegistration(RegistrationDto registrationDto) {

        Registration registration = dtoToRegistration(registrationDto);
        registrationRepository.save(registration);

        return registration;
    }




    @Override
    public String upDateRegistration(RegistrationDto registrationDto, Long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(() -> new RegistrationException("This Registration does not exist"));

        registration.setDriverFirstName(registrationDto.getDriverFirstName());
        registration.setAdditionalComments(registrationDto.getAdditionalComments());
        registration.setDriverLastName(registrationDto.getDriverLastName());
        registration.setCarNumberPlate(registrationDto.getCarNumberPlate());

        registration.setStarDate(registrationDto.getStarDate());
        registration.setStartTime(registrationDto.getStartTime());

        registration.setFinishDate(registrationDto.getFinishDate());
        registration.setFinishTime(registrationDto.getFinishTime());

        registrationRepository.save(registration);

        return "Updated";
    }


    @Override
    public String delecteRegistrationById(Long id) {

        registrationRepository.deleteById(id);
        return "Deleted";
    }

    @Override
    public List<RegistrationDto> sortByStatus(String status) {

        List<Registration> registrationList = registrationRepository.findAll();

        // Create a custom comparator based on the provided status
        Comparator<Registration> statusComparator = (reg1, reg2) -> {
            String status1 = reg1.getStatus();
            String status2 = reg2.getStatus();

            if (status1.equals(status)) {
                return status2.equals(status) ? 0 : -1;
            } else if (status2.equals(status)) {
                return 1;
            }
            return status1.compareTo(status2);
        };

        // Sort thehe registrations using the custom comparator
        registrationList.sort(statusComparator);

        return registrationList.stream()
                .map(this::registrationToDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<RegistrationDto> searchRegistration(String query) {
        List<Registration> registrations = registrationRepository.searchRegistration(query);

        return registrations.stream()
                        .map(this::registrationToDto)
                        .collect(Collectors.toList());

    }

    @Override
    public Registration findRegistrationById(Long id) {
        return registrationRepository.findById(id).orElseThrow(()-> new RuntimeException("Registration does not exist"));
    }


    private RegistrationDto registrationToDto(Registration registration){

        return RegistrationDto.builder()
                .additionalComments(registration.getAdditionalComments().toUpperCase())
                .driverFirstName(registration.getDriverFirstName().toUpperCase())
                .driverLastName(registration.getDriverLastName().toUpperCase())
                .carNumberPlate(registration.getCarNumberPlate().toUpperCase())
                .starDate(registration.getStarDate())
                .startTime(registration.getStartTime())
                .finishDate(registration.getFinishDate())
                .finishTime(registration.getFinishTime())
                .status(registration.getStatus().toUpperCase())
                .duration(registration.getDuration())
                .registrationId(registration.getRegistrationId())
                .build();
    }



    private Registration dtoToRegistration(RegistrationDto registrationDto){



        return Registration.builder()
                .carNumberPlate(registrationDto.getCarNumberPlate())
                .driverFirstName(registrationDto.getDriverFirstName())
                .driverLastName(registrationDto.getDriverLastName())
                .starDate(registrationDto.getStarDate())
                .startTime(registrationDto.getStartTime())
                .finishDate(registrationDto.getFinishDate())
                .finishTime(registrationDto.getFinishTime())
                .additionalComments(registrationDto.getAdditionalComments())
                .registrationId(registrationDto.getRegistrationId())
                .build();

    }


}
