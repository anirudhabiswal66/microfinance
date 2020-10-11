package com.lms.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.loan.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}