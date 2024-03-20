package com.example.cwork2carpark.service;

import com.example.cwork2carpark.dto.RegistrationDto;
import com.example.cwork2carpark.dto.RegistrationHistoryDto;
import com.example.cwork2carpark.entity.Registration;

import java.util.List;

public interface RegistrationHistoryServices{

void saveRegistrationHistory( Long id);

List<RegistrationHistoryDto> listOfHistoryById(Long id);



}
