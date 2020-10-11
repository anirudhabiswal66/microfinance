package com.lms.user.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.user.exception.UserNotFoundException;
import com.lms.user.model.User;
import com.lms.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Override
	public User getUser(Integer id) {
		Optional<User> user = null;
		try {
			user = userRepository.findById(id);
		} catch (Exception e) {
			LOGGER.error("User not in ID: " + id + "with erreor: " + e);
			throw new UserNotFoundException("User not in ID: " + id);
		}
		return user.get();
	}

	@Override
	@Transactional
	public User uploadUser(User user) {
		User saveUser = null;
		try {
			saveUser = userRepository.save(user);

		} catch (Exception e) {
			LOGGER.error("Errors during Save User:: " + e);
			throw new RuntimeException("Errors during Save User: " + e);
		}
		return saveUser;
	}
}
