package com.lms.emi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.emi.model.Emi;
import com.lms.emi.repository.EmiRepository;

@Service
public class EmiServiceImpl implements EmiService {

	@Autowired
	EmiRepository emiRepository;

	@Override
	@Transactional
	public List<Emi> storeEmi(List<Emi> setEmis) {
		List<Emi> saveAllEmi;
		try {
			saveAllEmi = emiRepository.saveAll(setEmis);

		} catch (Exception e) {
			throw new RuntimeException("Exception during saving EMI: " + e);
		}
		return saveAllEmi;
	}
}
