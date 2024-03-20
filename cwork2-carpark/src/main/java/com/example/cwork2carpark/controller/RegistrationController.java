package com.example.cwork2carpark.controller;

import com.example.cwork2carpark.dto.RegistrationDto;
import com.example.cwork2carpark.dto.RegistrationHistoryDto;
import com.example.cwork2carpark.entity.Registration;
import com.example.cwork2carpark.service.RegistrationHistoryServices;
import com.example.cwork2carpark.service.RegistrationServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class RegistrationController {


    private RegistrationServices registrationServices;
    private RegistrationHistoryServices registrationHistoryServices;

    public RegistrationController(RegistrationServices registrationServices, RegistrationHistoryServices registrationHistoryServices) {
        this.registrationServices = registrationServices;
        this.registrationHistoryServices = registrationHistoryServices;
    }



    @GetMapping("/table")
    public String getTables(Model model){
        List<RegistrationDto> registrationDtos = registrationServices.getAllRegistration();
        model.addAttribute("registrationDto", registrationDtos);
        return "registration-table";
    }

    //This is the Delecte
 @GetMapping  ("/car-park/{registrationId}/delete")
 public String deleteRegistration(@PathVariable("registrationId") Long id){
        registrationServices.delecteRegistrationById(id);
        return "redirect:/table";
 }

    @GetMapping("/car-park/add")
    public String getAdd(Model model){
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("registratinDto", registrationDto);
        return "registration-add";
    }

    @PostMapping("/car-park/add")
    public String postAdd(@ModelAttribute("registrationDto") RegistrationDto registrationDto){
         Registration registration =registrationServices.saveRegistration(registrationDto);
        Long id = registration.getRegistrationId();
        registrationHistoryServices.saveRegistrationHistory(id);
        return "redirect:/table";
    }




    @GetMapping("/car-park/{registrationId}/update")
    public String registerUpdate(@PathVariable("registrationId") Long id, Model model){


        Registration registration = registrationServices.findRegistrationById(id);
        model.addAttribute("registration", registration);
        return "Moon";
    }


    @PostMapping("/car-park/{registrationId}/update")
    private String registerUpdatePost(@PathVariable("registrationId") Long id, @ModelAttribute("registration") RegistrationDto registrationDto){

        registrationServices.upDateRegistration(registrationDto, id);
        registrationHistoryServices.saveRegistrationHistory(id);
        return "redirect:/table";
    }


    @GetMapping("/car-park/{registrationId}/history")
    public String getHistory(@PathVariable("registrationId")Long id, Model model){

        List<RegistrationHistoryDto> registrationHistoryDtos = registrationHistoryServices.listOfHistoryById(id);
        Registration registration = registrationServices.findRegistrationById(id);
        model.addAttribute("registration", registration);
        model.addAttribute("registrationHistory", registrationHistoryDtos);
        return "history-table";
    }

}
