package com.eurogeo;

import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User,Long> {
	User findByUserName(String userName);
	

}
