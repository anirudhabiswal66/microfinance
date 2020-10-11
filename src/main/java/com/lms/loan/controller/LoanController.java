package com.lms.loan.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.emi.model.Emi;
import com.lms.emi.service.EmiService;
import com.lms.loan.model.Loan;
import com.lms.loan.service.LoanService;
import com.lms.user.exception.InsufficientAmount;
import com.lms.user.exception.UserNotFoundException;
import com.lms.user.model.User;
import com.lms.user.service.UserService;
import com.lms.util.LMSUtils;

@RestController
@RequestMapping("/loan")
public class LoanController {

	@Autowired(required = false)
	LoanService loanService;

	@Autowired
	UserService userService;

	@Autowired
	EmiService emiService;

	@PostMapping("/create-loan/{userId}")
	public Loan createLoan(@PathVariable(value = "userId") Integer userId, @RequestParam("loanTitle") String loanTitle,
			@RequestParam(value = "interestRate", required = false, defaultValue = "12") Double interestRate,
			@RequestParam("principle") Double principle, @RequestParam("tenure") Integer tenure, Model model) {

		Loan createLoan = null;
		if (userId != null && userId != 0) {
			User user = null;
			try {
				user = userService.getUser(userId);
				if (user != null) {
					if (user.getAvailableLimit() >= principle) {
						Loan loan = null;
						List<Emi> calculateEMI = null;

						calculateEMI = LMSUtils.calculateEMI(principle, tenure, interestRate);
						loan = setLoanData(loanTitle, interestRate, principle, tenure, user);
						createLoan = loanService.createLoan(loan);

						for (Emi emi : calculateEMI) {
							emi.setLoan(createLoan);
						}
						emiService.storeEmi(calculateEMI);
						user.setUtilizedLimit(user.getUtilizedLimit() + principle);
						user.setAvailableLimit(user.getOveralLimit() - user.getUtilizedLimit());
						userService.uploadUser(user);
						
						// for display EMI details
						LMSUtils.showEmiDetails(calculateEMI, tenure, principle, interestRate);

					} else {
						throw new InsufficientAmount("Already you used all Loan Amount");
					}
				} else {
					throw new UserNotFoundException("User not Found on ID: " + userId);
				}
			} catch (InsufficientAmount | UserNotFoundException e) {
				throw e;
			} catch (Exception e) {
				throw new RuntimeException("Already you used all Loan Amount");
			}
		}
		return createLoan;
	}

	private Loan setLoanData(String loanTitle, Double interestRate, Double principle, Integer tenure, User user) {
		Loan loan;
		loan = new Loan();
		loan.setLoanTitle(loanTitle);
		loan.setUser(user);
		loan.setIntrestOfRate(interestRate);
		loan.setTenure(tenure);
		loan.setLoanAmount(principle + LMSUtils.calculateSimpleIntesest(principle, interestRate, tenure));
		loan.setPrincipal(principle);
		loan.setTenure(12);
		return loan;
	}
}
