package com.vikas.loginApi;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
@Path("/login")
public class userloginResource {
	userRepository repo1 = new userRepository();
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED})
    public Response authenticateUser(userlogin user) {

        try {
       	  String name = user.getName().toString();
       	  String password = user.getPassword().toString();
       	  String authMessage = null;
       	  String token = null;
            // Authenticate the user using the credentials provided
            authMessage = authenticate(name, password);

            // Issue a token for the user
            if(authMessage == "Success"){
            	token = generateNewToken();
            	return Response.ok("{'message':'success','accessToken':"+token+"}").build();
            }else{
            	return Response.ok(ImmutableMap.of("message", authMessage)).build();
            }
            	

            // Return the token on the response
            
            

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }      
    }

    private String authenticate(String username, String password) throws Exception {
        try{
        	String message = null;
        	List<userRegister> userList = new ArrayList<>();
        	userList = repo1.getallUsers();
        	for(int i=0; i< userList.size(); i++){
        		String name = userList.get(i).getName().toString();
        		String pass = userList.get(i).getPassword().toString();
        		if(name.equals(username)  && pass.equals(password)){
        			message = "Success";
        		}
        		else{
        			message = "unauthorized user";
        		}
        	}
        	return message;
        }catch (Exception e) {
        	
            return null;
        } 
    }

        
    private static final SecureRandom secureRandom = new SecureRandom(); 
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

}
