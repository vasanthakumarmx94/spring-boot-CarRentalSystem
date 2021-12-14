package com.neosoft;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.neosoft.model.UserBean;
import com.neosoft.repository.UserRepository;




@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository repo;
	
	@Test
	public void testCreateUser() {
		UserBean user = new UserBean();
		user.setName("vasanthakumar");
		user.setMobile("8787666544");
		user.setState("Karnataka");
		user.setCity("Tumakur");
		user.setPincode("545445");
		user.setAddress("Singanahalli at post");
		user.setEmail("vasu@gmail.com");
		user.setPassword("vasu123");
	
		UserBean savedUser = repo.save(user);
		UserBean existUser = entityManager.find(UserBean.class, savedUser.getUserid());
		
		assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
		
	}
	
	@Test
	public void testFindByEmail() {
		String email = "nam@codejava.net";
		UserBean user = repo.findByEmail(email);
		
		assertThat(user.getEmail()).isEqualTo(email);
	}
	
	
}
