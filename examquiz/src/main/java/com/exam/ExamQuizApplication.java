package com.exam;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamQuizApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ExamQuizApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Override
	public void run(String... args) throws Exception {

		User user=new User();
		user.setFirstName("Kuldeep");
		user.setLastName("joshi");
		user.setProfile("default.png");
		user.setPassword(this.bCryptPasswordEncoder.encode("kuldeep"));
		user.setPhone("9079188341");
		user.setEmail("kuldeep@gmail.com");
		user.setUsername("kuldeep10");

		Role role=new Role();
		role.setRolId(44L);
		role.setRoleName("ADMIN");

		Set<UserRole> useroles=new HashSet<>();
		UserRole userRole=new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);

		useroles.add(userRole);

		User user1 = this.userService.createUser(user, useroles);
		System.out.println(user1.getUsername());

	}
}
 