package com.lms.user.excelfile.fileservice;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lms.user.model.User;
import com.lms.user.repository.UserRepository;
import com.lms.util.LMSUtils;

@Service
public class FileServicesImpl implements FileServices {

	public static final Logger LOGGER = LoggerFactory.getLogger(FileServicesImpl.class);

	@Autowired
	UserRepository userRepository;

	@Transactional
	public List<User> storeUserDataByExcel(MultipartFile file) {
		List<User> allUserFromDb = null;
		List<User> listUers = null;
		try {
			listUers = LMSUtils.parseExcelFile(file.getInputStream());
			allUserFromDb = userRepository.saveAll(listUers);
		} catch (IOException e) {
			LOGGER.error("Errors during save all Users: " + e);
			throw new RuntimeException("Errors during save all Users: " + e);
		}
		return allUserFromDb;

	}

}
