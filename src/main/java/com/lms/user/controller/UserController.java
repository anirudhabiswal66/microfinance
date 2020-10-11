package com.lms.user.controller;

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
import com.lms.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	FileServices fileServices;

	@PostMapping("/upload/excel")
	public List<User> uploadUserExcelFile(@RequestParam("uploadfile") MultipartFile file, Model model) {
		List<User> userStore = null;
		try {
			userStore = fileServices.storeUserDataByExcel(file);

			// model.addAttribute("message", "File uploaded successfully!");
		} catch (Exception e) {
			throw new RuntimeException("Fail! -> uploaded filename: " + file.getOriginalFilename());
			// model.addAttribute("message", "Fail! -> uploaded filename: " +
			// file.getOriginalFilename());
		}
		return userStore;
	}

}
