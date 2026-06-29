package com.sharmashivansh2907.SecurityAPp.SecurityApplication;

import com.sharmashivansh2907.SecurityAPp.SecurityApplication.entity.User;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.serviceImpl.JwtServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationTests {

	@Autowired
	private JwtServiceImpl jwtService;

	@Test
	void contextLoads() {
	}

	@Test
	void contextLoad(){
		new User();
		User user = User.builder()
				.ID(1L)
				.email("Shivansh@gmail.com")
				.password("1234")
				.build();

		String token = jwtService.generateToken(user);
		System.out.println(token);

		Long ID = jwtService.getUserIDFromToken(token);
		System.out.println(ID);
	}


}
