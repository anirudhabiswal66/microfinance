package com.lms.loan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lms.emi.service.EmiService;
import com.lms.loan.model.Loan;
import com.lms.loan.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	LoanRepository loanRepository;

	@Autowired
	EmiService emiService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Loan createLoan(Loan loan) {
		Loan saveLoan = null;
		try {
			saveLoan = loanRepository.save(loan);

		} catch (Exception e) {
			throw new RuntimeException("Error during store data into loan: " + e);
		}
		return saveLoan;
	}

}
