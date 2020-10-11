package com.lms.user.excelfile.fileservice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lms.user.model.User;

@Service
public interface FileServices {

	public List<User> storeUserDataByExcel(MultipartFile file);

}
