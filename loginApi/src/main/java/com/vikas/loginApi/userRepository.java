package com.vikas.loginApi;

import java.util.ArrayList;
import java.util.List;


public class userRepository {
	List<userRegister> allUsers;
	
	public userRepository(){
		allUsers = new ArrayList<>();
		userRegister ur = new userRegister();
        ur.setName("Abinash");
        ur.setPassword("simpragma123");
        ur.setUserId(101);
        ur.setAddress("bangalore");
        ur.setEmail("abinash@simpragma.com");
        allUsers.add(ur);
       
	}
	
	public List<userRegister> getallUsers(){
		return allUsers;
	}

	public void register(userRegister userRegisters) {
		
		allUsers.add(userRegisters);
	}
}
