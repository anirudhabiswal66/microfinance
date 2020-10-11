package com.lms.emi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.emi.model.Emi;

public interface EmiRepository extends JpaRepository<Emi, Integer> {

}
