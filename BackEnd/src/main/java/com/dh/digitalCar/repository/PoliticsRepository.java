package com.dh.digitalCar.repository;

import com.dh.digitalCar.entities.Politics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliticsRepository extends JpaRepository<Politics, Integer> {
}
