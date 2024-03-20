package com.example.cwork2carpark.repository;

import com.example.cwork2carpark.entity.RegistrationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegistrationHistoryRepository extends JpaRepository<RegistrationHistory,Long> {

List<RegistrationHistory> findByRegistrationRegistrationId(Long id);
}
