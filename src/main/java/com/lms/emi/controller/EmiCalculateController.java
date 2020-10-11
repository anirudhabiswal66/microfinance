package com.lms.emi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.emi.model.Emi;
import com.lms.emi.service.EmiService;
import com.lms.util.LMSUtils;

@RestController
@RequestMapping("/emi")
public class EmiCalculateController {

	@Autowired
	EmiService emiService;

	@PostMapping("/calculate")
	public List<Emi> createLoan(@RequestParam("loanTitle") String loanTitle,
			@RequestParam(value = "interestRate", required = false, defaultValue = "12") Double interestRate,
			@RequestParam("principle") Double principle, @RequestParam("tenure") Integer tenure) {

		List<Emi> calculateEMI = null;
		calculateEMI = LMSUtils.calculateEMI(principle, tenure, interestRate);

		// display to UI
		LMSUtils.showEmiDetails(calculateEMI, tenure, principle, interestRate);

		return calculateEMI;
	}

}
