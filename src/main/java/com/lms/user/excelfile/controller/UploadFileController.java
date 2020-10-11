package com.lms.user.excelfile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lms.user.excelfile.fileservice.FileServices;
import com.lms.user.model.User;

@RestController
@RequestMapping("/excel")
public class UploadFileController {

	@Autowired
	FileServices fileServices;

	@PostMapping("/upload")
	public List<User> uploadUserExcelFile(@RequestParam("uploadfile") MultipartFile file, Model model) {
		List<User> userStore = null;
		try {
			userStore = fileServices.storeUserDataByExcel(file);

			// model.addAttribute("message", "File uploaded successfully!");
		} catch (Exception e) {
			throw new RuntimeException("Errors during save all Users: " + e);
			// model.addAttribute("message", "Fail! -> uploaded filename: " +
			// file.getOriginalFilename());
		}
		return userStore;
	}
}