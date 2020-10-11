package com.lms.user.service;

import com.lms.user.model.User;

public interface UserService {
	User getUser(Integer id);

	User uploadUser(User user);

}
